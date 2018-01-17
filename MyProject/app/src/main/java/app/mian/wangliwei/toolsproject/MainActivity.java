package app.mian.wangliwei.toolsproject;

//import android.app.Fragment;
//import android.app.FragmentTransaction;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import app.mian.wangliwei.toolsproject.bean.Component.DaggerMainActivityComponent;
import app.mian.wangliwei.toolsproject.bean.Factory;
import app.mian.wangliwei.toolsproject.view.BookActivity;
import app.mian.wangliwei.toolsproject.view.LoginActivity;
import app.mian.wangliwei.toolsproject.view.fragment.AFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements AFragment.OnFragmentInteractionListener {
    Intent intent;
    private static boolean enableExit = false;
    private static boolean immersive = false;
    private static MyHandler myHandler = new MyHandler();
    private int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

    private Fragment aFragment;

    @Inject
    Factory factory;

    @BindView(R.id.sample_text)
    TextView tvLogin;

    @BindView(R.id.activity_book)
    TextView tvBook;

    @BindView(R.id.text_version)
    TextView tvVersion;

    @BindView(R.id.add_fragment)
    TextView textFragment;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTransParent();
        ButterKnife.bind(this);
        DaggerMainActivityComponent.create().inject(this);

        // Example of a call to a native method
        tvLogin.setText(stringFromJNI());
        //Toast.makeText(this,"This is update",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        /**
         * only set in onCreate once
         */
        //setTransParent();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(immersive & hasFocus){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    |View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            |View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @OnClick(R.id.text_version)
    public void toast(){
        Toast.makeText(this,factory.getProduct().getStr(),Toast.LENGTH_LONG).show();
    }


    @OnClick(R.id.sample_text)
    public void setTv(TextView tv1) {
        tv1.setText("Butter Knife");
        intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.activity_book)
    public void startBookView(){
        intent = new Intent(this, BookActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.add_fragment)
    public void addFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        aFragment = AFragment.newInstance("test","1");

        if(aFragment != null & !aFragment.isAdded()){
            ft.add(R.id.fragment_content,aFragment);
            ft.show(aFragment);
        }
        ft.commit();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    enableExit = false;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(!enableExit) {
                enableExit = true;
                Toast.makeText(this,"再按一次退出",Toast.LENGTH_LONG).show();
                myHandler.sendEmptyMessageDelayed(0,3000);
            }else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void setTransParent(){
        getWindow().getDecorView().setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        getSupportActionBar().hide();
    }
}
