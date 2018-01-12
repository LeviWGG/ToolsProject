package app.mian.wangliwei.myproject;

//import android.app.Fragment;
//import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import app.mian.wangliwei.myproject.view.BookActivity;
import app.mian.wangliwei.myproject.view.LoginActivity;
import app.mian.wangliwei.myproject.view.fragment.AFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements AFragment.OnFragmentInteractionListener {
    Intent intent;
    private static boolean enableExit = false;
    private static MyHandler myHandler = new MyHandler();

    private Fragment aFragment;

    @BindView(R.id.sample_text)
    TextView tvLogin;

    @BindView(R.id.activity_book)
    TextView tvBook;

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
        ButterKnife.bind(this);

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
        tvLogin.setText(stringFromJNI());

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
}
