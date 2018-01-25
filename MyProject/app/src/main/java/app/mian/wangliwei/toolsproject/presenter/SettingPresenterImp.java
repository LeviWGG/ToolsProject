package app.mian.wangliwei.toolsproject.presenter;


import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.Glide;

import app.mian.wangliwei.toolsproject.view.ISettingView;

public class SettingPresenterImp implements ISettingPresenter {
    private ISettingView iSettingView;
    private Handler uiHandler;

    public SettingPresenterImp(ISettingView iSettingView) {
        this.iSettingView = iSettingView;
        uiHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void clearMemory() {
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Glide.get(iSettingView.getContext()).clearMemory();
            }
        },0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(iSettingView.getContext()).clearDiskCache();
            }
        }).start();
    }
}
