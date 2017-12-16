package com.example.amyas.customwidget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * author: Amyas
 * date: 2017/12/8
 */

public class MockScrollView extends ViewGroup {
    private int mScreenHeight;
    private int mScreenWidth;
    private int mLastY;
    private Scroller mScroller;
    private int mStart;
    private int mEnd;
    private int mockScrollViewHeight;
    public MockScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        WindowManager manager = this.getWindowManager();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        manager.getDefaultDisplay().getMetrics(outMetrics);
//        int width = outMetrics.widthPixels;
//        int height = outMetrics.heightPixels;
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
//        System.out.println("屏幕高为"+mScreenHeight);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
//        System.out.println(" 子view 个数为： "+count);
        MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
        mockScrollViewHeight = layoutParams.height = mScreenHeight*count;

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility()!=GONE){
                childView.layout(l,mScreenHeight*i,r, mScreenHeight*(i+1));
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("onTouchEvent: get in here");
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
//                System.out.println("get in on touch event down");
                mStart = getScrollY();
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("get in on touch event move");
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                System.out.println("event move: isFinished == false");
                int dy = mLastY-y;
                System.out.println("event move: dy = "+dy);
                if (getScrollY()<0){
//                    System.out.println(" getScrollY() 小于0");
                    dy = 0;
                }
                // TODO: 2017/12/8 这里的getScrollY()>getViewHeight()-mScreenHeight计算有问题
                if (getScrollY()>getViewHeight()-mScreenHeight){
                    System.out.println("getHeight():"+getHeight());
                    System.out.println("mScreenHeight:"+mScreenHeight);
                    System.out.println(" getScrollY()>getHeight()-mScreenHeight 小于0");
                    dy = 0;
                }
                System.out.println("event move: dy = "+dy);
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
//                System.out.println("get in on touch event up");
                mEnd = getScrollY();
                int travel = mEnd-mStart;
                if (travel>0){
                    if (travel<mScreenHeight/3){
                        mScroller.startScroll(0, mEnd, 0, -travel);
                    }else {
                        mScroller.startScroll(0, mEnd, 0, mScreenHeight-travel);
                    }
                }else {
                    if ((-travel)<mScreenHeight/3){
                        mScroller.startScroll(0, mEnd,0, travel);
                    }else {
                        mScroller.startScroll(0, mEnd,0, mScreenHeight+travel);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }
    private int getViewHeight(){
        return mockScrollViewHeight;
    }

}
