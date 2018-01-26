package app.mian.wangliwei.toolsproject.view.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleBarView extends View {
    private Paint rectPaint;
    private Paint progressPaint;

    public CircleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setColor(Color.RED);

        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setColor(Color.BLUE);
        progressPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float x = 50;
        float y = 50;

        RectF rectF = new RectF(x,y,x+300,y+300);

        canvas.drawArc(rectF,90,270,false,progressPaint);
        canvas.drawRect(rectF,rectPaint);
    }
}
