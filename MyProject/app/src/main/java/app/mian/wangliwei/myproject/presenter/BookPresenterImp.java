package app.mian.wangliwei.myproject.presenter;

import android.util.Log;

import com.google.gson.GsonBuilder;

import app.mian.wangliwei.myproject.http.BookService;
import app.mian.wangliwei.myproject.model.Book;
import app.mian.wangliwei.myproject.view.IBookView;
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

    public BookPresenterImp(IBookView iBookView){
        this.iBookView = iBookView;

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
}
