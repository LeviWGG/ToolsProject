package app.mian.wangliwei.toolsproject.view.widget;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CategoryItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;

    public CategoryItemDecoration(int color) {
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();

        Rect rect = new Rect();
        rect.left = parent.getLeft();
        rect.right = parent.getRight() - parent.getPaddingRight();

        for(int i=0;i<childCount;i++) {
            View childView = parent.getChildAt(i);
            rect.top = childView.getBottom();
            rect.bottom = rect.top + 1;

            c.drawRect(rect,mPaint);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom += 1;
    }
}
