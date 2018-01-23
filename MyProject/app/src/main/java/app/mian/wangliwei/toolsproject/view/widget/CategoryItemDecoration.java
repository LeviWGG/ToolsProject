package app.mian.wangliwei.toolsproject.view.widget;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import app.mian.wangliwei.toolsproject.presenter.IRecyclerViewPresenter;

public class CategoryItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private TextPaint mTextPaint;
    private IRecyclerViewPresenter iRecyclerViewPresenter;

    private final static int mHeight = 50;//分割区域高度

    public CategoryItemDecoration(int color, IRecyclerViewPresenter iRecyclerViewPresenter) {
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);

        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(50);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);

        this.iRecyclerViewPresenter = iRecyclerViewPresenter;
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
            int pos = parent.getChildAdapterPosition(childView);
            int preClass = iRecyclerViewPresenter.getClass(pos-1);
            int currentClass = iRecyclerViewPresenter.getClass(pos);
            int nextClass = iRecyclerViewPresenter.getClass(pos+1);

            if(preClass==currentClass) {
                if(pos != 0 && childView.getBottom() > 120)continue;
                rect.bottom = mHeight;
                rect.top = 0;
            }

            if(preClass != currentClass){
                //高度不小于50，作悬浮效果
                rect.bottom = Math.max(childView.getTop(),mHeight);
                rect.top = rect.bottom - mHeight;
            }

            if(currentClass != nextClass && childView.getBottom() < 50) {
                //下一组最近的一个靠近时移动上一组悬浮框
                rect.bottom = childView.getBottom();
                rect.top = rect.bottom-50;
            }

            c.drawRect(rect,mPaint);
            currentClass++;

            c.drawText("班级:"+currentClass,rect.left+10,rect.bottom-5,mTextPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect,view,parent,state);

        int pos = parent.getChildAdapterPosition(view);
        int preClass = iRecyclerViewPresenter.getClass(pos-1);
        int currentClass = iRecyclerViewPresenter.getClass(pos);

        if(pos == 0 || preClass != currentClass) {
            //给第一个item预留空间
            outRect.top = mHeight;
        }
    }
}
