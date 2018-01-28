package app.mian.wangliwei.toolsproject.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import app.mian.wangliwei.toolsproject.http.BookService;
import app.mian.wangliwei.toolsproject.http.ServiceFactory;
import app.mian.wangliwei.toolsproject.model.Book;
import app.mian.wangliwei.toolsproject.utils.MessageEvent;
import app.mian.wangliwei.toolsproject.view.IBookView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;

/**
 * Created by wangliwei on 2018/1/6.
 */

public class BookPresenterImp implements IBookPresenter {
    private static final String BASE_URL = "https://api.douban.com/v2/";

    IBookView iBookView;
    Handler uihandler;

    public BookPresenterImp(IBookView iBookView){
        this.iBookView = iBookView;
        uihandler = new Handler(Looper.getMainLooper());
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void setMessage(MessageEvent event){
        iBookView.setBookText(event.getMessage());
    }

    @Override
    public void getSearchBook(String name, String tag, int count, int start) {
        Retrofit retrofit = new ServiceFactory().create(BASE_URL);
        BookService bookService = retrofit.create(BookService.class);
        Observable<Book> observable = bookService.getSearchBook(name,tag,count,start);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Book, ObservableSource<Book.BooksBean>>() {
                    @Override
                    public ObservableSource<Book.BooksBean> apply(Book book) throws Exception {
                        return Observable.fromIterable(book.getBooks());
                    }
                })
                .filter(new Predicate<Book.BooksBean>() {
                    @Override
                    public boolean test(Book.BooksBean booksBean) throws Exception {
                        if(booksBean != null)return true;
                        return false;
                    }
                })
                .subscribe(new Consumer<Book.BooksBean>() {
                    @Override
                    public void accept(Book.BooksBean booksBean) throws Exception {
                        if(booksBean == null) {
                            iBookView.setBookText("没找到书本信息");
                            return;
                        }
                        iBookView.setBookText(booksBean.getAuthor_intro());
                    }
                });
    }

    @Override
    public void getBookUseOkhttp(String name, String tag, int count, int start) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().get().url(BASE_URL+"book/search"+
                "?q="+name+"&tag="+tag+"&count="+count+"&start="+start).build();
        okhttp3.Call call = okHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, final okhttp3.Response response) throws IOException {
                uihandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            iBookView.setBookText(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },0);

            }
        });
    }

    @Override
    public void onDestroy() {
        Log.d("book","book present destroy");
        iBookView = null;
        EventBus.getDefault().unregister(this);
    }
}
