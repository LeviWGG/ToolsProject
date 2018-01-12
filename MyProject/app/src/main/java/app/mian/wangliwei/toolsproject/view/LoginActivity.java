package app.mian.wangliwei.toolsproject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.presenter.ILoginPresenter;
import app.mian.wangliwei.toolsproject.presenter.LoginPresenterImp;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    @BindView(R.id.user)
    EditText editUser;

    @BindView(R.id.password)
    EditText editPwd;

    @BindView(R.id.login)
    Button btnLogin;

    @BindView(R.id.clear)
    Button btnClear;

    Unbinder unbinder;

    ILoginPresenter mILoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);

        mILoginPresenter = new LoginPresenterImp(this);

    }

    @OnClick(R.id.login)
    public void login(){
        mILoginPresenter.login(editUser.getText().toString(),editPwd.getText().toString());
    }

    @OnClick(R.id.clear)
    public void clear(){
        mILoginPresenter.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();

        Log.d("login","login destroy");
    }

    @Override
    public void onClear() {
        editUser.setText("");
        editPwd.setText("");
    }

    @Override
    public void onLogin(boolean result) {
        if(result){
            Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Login fail",Toast.LENGTH_LONG).show();
        }
    }
}
