package app.mian.wangliwei.toolsproject.presenter;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import app.mian.wangliwei.toolsproject.bean.ScrollEvent;
import app.mian.wangliwei.toolsproject.view.fragment.IRecyclerViewFg;


public class RecyclerViewPresenterImp implements IRecyclerViewPresenter {
    private IRecyclerViewFg iRecyclerViewFg;
    private Handler uiHandler;
    private Context context;

    public RecyclerViewPresenterImp(IRecyclerViewFg iRecyclerViewFg){
        this.iRecyclerViewFg = iRecyclerViewFg;
        this.context = iRecyclerViewFg.getFgContext();
        uiHandler = new Handler(Looper.getMainLooper());
        EventBus.getDefault().register(this);
    }

    @Override
    public int getClass(int pos) {
        //view标志的获取方法
        return pos/10;
    }

    @Subscribe
    public void fbScroEvent(ScrollEvent scrollEvent) {
        iRecyclerViewFg.scrollToTop();
    }
}
