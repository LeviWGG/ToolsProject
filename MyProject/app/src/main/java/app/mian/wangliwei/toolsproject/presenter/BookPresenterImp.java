package app.mian.wangliwei.toolsproject.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import app.mian.wangliwei.toolsproject.http.BookService;
import app.mian.wangliwei.toolsproject.model.Book;
import app.mian.wangliwei.toolsproject.view.IBookView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    }

    @Override
    public void getSearchBook(String name, String tag, int count, int start) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
        BookService bookService = retrofit.create(BookService.class);
        Call<Book> call = bookService.getSearchBook(name,tag,count,start);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                iBookView.setBookText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d("book","get book fail");
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
}
