package app.mian.wangliwei.toolsproject.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.presenter.ISettingPresenter;
import app.mian.wangliwei.toolsproject.presenter.SettingPresenterImp;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingActivity extends AppCompatActivity implements ISettingView {
    private ISettingPresenter iSettingPresenter;
    private Unbinder unbinder;

    @BindView(R.id.btn_clear_memory)
    Button btnClearMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        iSettingPresenter = new SettingPresenterImp(this);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_clear_memory)
    public void clearMemory() {
        iSettingPresenter.clearMemory();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
