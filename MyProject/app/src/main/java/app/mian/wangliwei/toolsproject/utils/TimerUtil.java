package app.mian.wangliwei.toolsproject.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

/**
 * Created by wlw on 2018/3/19.
 */

public class TimerUtil {
    private static Handler uiHandler;
    public static void setMinuteTimer(TextView textView) {
        if(uiHandler == null)uiHandler = new Handler(Looper.getMainLooper());

        textView.setEnabled(false);
        uiHandler.post(getMinuteRunnable(textView,uiHandler));
    }

    public static Runnable getMinuteRunnable(final TextView textView,
                                             final Handler handler) {
        return new Runnable() {
            int second = 60;
            @Override
            public void run() {
                this.update();
                second--;
                if(second < 0) {
                    textView.setText("获取验证码");
                    textView.setEnabled(true);
                    return;
                }
                handler.postDelayed(this,1000);

            }
            void update() {
                if(textView != null)
                    textView.setText(second+" s");
            }
        };
    }

    public static void detach() {
        if (uiHandler == null)return;
        uiHandler.removeCallbacksAndMessages(null);
    }
}
