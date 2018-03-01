package com.example.amyas.grocery.scroll.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * author: Amyas
 * date: 2017/12/14
 */

public class ScrollView extends View {
    public static final String TAG = "ScrollView";
    private Scroller mScroller;
    private int lastX;
    private int lastY;

    public ScrollView(Context context) {
        super(context);
        mScroller = new Scroller(getContext());
    }

    public ScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ViewGroup group = (ViewGroup) getParent();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: action_up");

                mScroller.startScroll(group.getScrollX(),
                        group.getScrollY(),
                        -group.getScrollX(),
                        -group.getScrollY(), 1000);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x-lastX;
                int offsetY = y-lastY;
                ((View)getParent()).scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;


        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            Log.e(TAG, "computeScroll: ");
            ((View) getParent()).scrollTo(mScroller.getCurrX(),
                    mScroller.getCurrY());
            invalidate();
        }
    }
}
