package app.mian.wangliwei.toolsproject.view.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

import java.text.DecimalFormat;

import app.mian.wangliwei.toolsproject.utils.ColorUtil;

public class CircleBarView extends View {
    private Paint rectPaint;
    private Paint progressPaint;
    private Paint bgPaint;
    private float startAngle;
    private float sweeapAngle;
    private float progressSweepAngle;
    private float progress;
    private float maxProgress;
    private CircleAnim anim;
    private OnAnimationListener onAnimationListener;
    private TextView textView;

    public CircleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        anim = new CircleAnim();

        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setColor(Color.TRANSPARENT);

        bgPaint = new Paint();
        setPaint(bgPaint);
        bgPaint.setColor(Color.GRAY);

        progressPaint = new Paint();
        setPaint(progressPaint);
        progressPaint.setColor(Color.GREEN);

        startAngle = 135;
        sweeapAngle = 270;

        progress = 0;
        maxProgress = 100;

        setOnAnimationListener(new OnAnimationListener() {
            ColorUtil colorUtil = new ColorUtil(Color.GREEN,Color.RED);
            @Override
            public String getProgressNum(Paint paint,float interpolatedTime, float progress, float maxProgress) {
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String str = decimalFormat.format(interpolatedTime * progress / maxProgress *100);

                paint.setColor(colorUtil.getGradient(interpolatedTime));

                return str;
            }
        });
    }

    private void setPaint(Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(40);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float x = 50;
        float y = 50;

        RectF rectF = new RectF(x,y,x+300,y+300);

        canvas.drawArc(rectF,startAngle,sweeapAngle,false,bgPaint);
        canvas.drawArc(rectF,startAngle,progressSweepAngle,false,progressPaint);
        canvas.drawRect(rectF,rectPaint);
    }

    public class CircleAnim extends Animation {
        public CircleAnim() {}

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);

            progressSweepAngle = interpolatedTime * sweeapAngle * progress / maxProgress;
            textView.setText(onAnimationListener.getProgressNum(progressPaint,interpolatedTime,progress,maxProgress)+"%");
            postInvalidate();
        }
    }

    public void setProcessTime(float progress,int time) {
        this.progress = progress;
        anim.setDuration(time);
        this.startAnimation(anim);
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void setOnAnimationListener(OnAnimationListener onAnimationListener) {
        this.onAnimationListener = onAnimationListener;
    }

    /**
     * 给外部调用的进度设置接口，可设置进度条颜色
     */
    public interface OnAnimationListener {
        String getProgressNum(Paint paint,float interpolatedTime,float progress,float maxProgress);
    }
}
