package app.mian.wangliwei.toolsproject.presenter;

/**
 * Created by wangliwei on 2018/1/6.
 */

public interface IBookPresenter {
    void getSearchBook(String name,String tag,int count,int start);
    void getBookUseOkhttp(String name,String tag,int count,int start);
    void onDestroy();
}
