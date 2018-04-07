package app.mian.wangliwei.toolsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import app.mian.wangliwei.toolsproject.utils.TimerUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Pin1Activity extends AppCompatActivity {

    @BindView(R.id.text_test)
    TextView textTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        ButterKnife.bind(this);
        TimerUtil.setMinuteTimer(textTest);
    }
}
