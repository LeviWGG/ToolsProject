package app.mian.wangliwei.toolsproject.model;

import app.mian.wangliwei.toolsproject.presenter.ILoginPresenter;

/**
 * Created by wangliwei on 2018/1/5.
 */

public class User implements IUser {
    String user = "test";
    String pwd = "123";
    ILoginPresenter mILoginPresener;

    public User(ILoginPresenter iLoginPresenter){
        this.mILoginPresener = iLoginPresenter;
    }

    @Override
    public boolean login(String user, String pwd) {
        if(this.user.equals(user) && this.pwd.equals(pwd))return true;

        return false;
    }
}
