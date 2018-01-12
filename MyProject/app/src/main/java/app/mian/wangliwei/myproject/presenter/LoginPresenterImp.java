package app.mian.wangliwei.myproject.presenter;

import android.os.Handler;
import android.os.Looper;

import app.mian.wangliwei.myproject.model.IUser;
import app.mian.wangliwei.myproject.model.User;
import app.mian.wangliwei.myproject.view.ILoginView;

/**
 * Created by wangliwei on 2018/1/5.
 */

public class LoginPresenterImp implements ILoginPresenter {
    ILoginView mILoginView;
    IUser mIUser;
    Handler handler;

    public LoginPresenterImp(ILoginView iLoginView){
        this.mILoginView = iLoginView;
        mIUser = new User(this);
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void clear() {
        mILoginView.onClear();
    }

    @Override
    public void login(String user, String pwd) {
        mILoginView.onLogin(mIUser.login(user, pwd));
    }
}
