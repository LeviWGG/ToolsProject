package app.mian.wangliwei.toolsproject.http;

import app.mian.wangliwei.toolsproject.model.Book;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wangliwei on 2018/1/6.
 */

public interface BookService {
    @GET("book/search")
    Call<Book> getSearchBook(@Query("q") String name,
                             @Query("tag") String tag,
                             @Query("count") int count,
                             @Query("start") int start);
}
