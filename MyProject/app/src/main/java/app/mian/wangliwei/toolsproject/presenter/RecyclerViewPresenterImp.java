package app.mian.wangliwei.toolsproject.presenter;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;


public class RecyclerViewPresenterImp implements IRecyclerViewPresenter {
    private Handler uiHandler;
    private Context context;

    public RecyclerViewPresenterImp(Context context){
        this.context = context;
        uiHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public int getClass(int pos) {
        //view标志的获取方法
        return pos/10;
    }
}
