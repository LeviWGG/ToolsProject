package app.mian.wangliwei.toolsproject.view.widget;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

public class CategoryItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private TextPaint mTextPaint;

    public CategoryItemDecoration(int color) {
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);

        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(50);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c,parent,state);

        int childCount = parent.getChildCount();

        Rect rect = new Rect();
        rect.left = parent.getLeft();
        rect.right = parent.getRight() - parent.getPaddingRight();

        for(int i=0;i<childCount;i++) {
            View childView = parent.getChildAt(i);

            rect.bottom = childView.getTop();
            rect.top = rect.bottom - 1;

            c.drawRect(rect,mPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childCount = parent.getChildCount();

        Rect rect = new Rect();
        rect.left = parent.getLeft();
        rect.right = parent.getRight() - parent.getPaddingRight();

        for(int i=0;i<childCount;i++) {
            View childView = parent.getChildAt(i);
            View firstView = parent.getChildAt(0);
            int firstPos = parent.getChildAdapterPosition(firstView);
            int pos = parent.getChildAdapterPosition(childView);
            int preClass = (firstPos)/10;
            int currentClass = (firstPos+1)/10;
            int nextClass = (firstPos+2)/10;



            if(preClass==currentClass) {
                rect.bottom = 50;
                rect.top = 0;
            }

            if(pos == 0 || (pos+1)%10 == 0){
                rect.bottom = Math.max(childView.getTop(),50);
                rect.top = rect.bottom - 50;
                currentClass++;
            }else if(currentClass != nextClass && firstView.getBottom() < 50) {
                rect.bottom = firstView.getBottom();
                rect.top = rect.bottom-50;
            }

            c.drawRect(rect,mPaint);

            c.drawText("班级:"+currentClass,rect.left+10,rect.bottom,mTextPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect,view,parent,state);

        int pos = parent.getChildAdapterPosition(view);

        if(pos == 0 || (pos+1)%10==0) {
            //给第一个item预留空间
            outRect.top = 50;
        }
    }
}
