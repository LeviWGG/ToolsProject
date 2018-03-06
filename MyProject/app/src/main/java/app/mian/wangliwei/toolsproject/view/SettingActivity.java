package app.mian.wangliwei.toolsproject.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.aidl.IFoodManager;
import app.mian.wangliwei.toolsproject.presenter.ISettingPresenter;
import app.mian.wangliwei.toolsproject.presenter.SettingPresenterImp;
import app.mian.wangliwei.toolsproject.service.FoodService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingActivity extends AppCompatActivity implements ISettingView {
    private ISettingPresenter iSettingPresenter;
    private Unbinder unbinder;
    private IFoodManager iFoodManager;

    @BindView(R.id.btn_clear_memory)
    Button btnClearMemory;

    @BindView(R.id.btn_get_foodnum)
    Button btnGetFoodNum;

    @BindView(R.id.btn_add_foodnum)
    Button btnAddFoodNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        iSettingPresenter = new SettingPresenterImp(this);
        unbinder = ButterKnife.bind(this);

        Intent intent = new Intent(this, FoodService.class);
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    @OnClick({R.id.btn_clear_memory,R.id.btn_get_foodnum,R.id.btn_add_foodnum})
    public void clearMemory(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_clear_memory :
                iSettingPresenter.clearMemory();
                break;
            case R.id.btn_get_foodnum :
                try {
                    Toast.makeText(this,"get num : "+iFoodManager.getFoodNum(),Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_add_foodnum :
                try {
                    iFoodManager.addFoodNum(10);
                    Toast.makeText(this,"add success",Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iFoodManager = IFoodManager.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unbindService(serviceConnection);
    }
}
