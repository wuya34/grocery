package com.example.amyas.grocery.scroll.group;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * author: Amyas
 * date: 2017/12/14
 */

public class DragViewGroup extends FrameLayout {
    public static final String TAG = "DragViewGroup";
    private ViewDragHelper mViewDragHelper;
    private View slidePane, contentPane;
    private int mWidth;
    private float scale = 1f;
    private ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
//            Log.e(TAG, "tryCaptureView: child " + child);
            return child == contentPane;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            Log.e(TAG, "onViewReleased: 查看：" + contentPane.getLeft());
            if (contentPane.getLeft() < mWidth) {
                Log.e(TAG, "onViewReleased: ");
                mViewDragHelper.smoothSlideViewTo(contentPane, 0, 0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            } else {
                mViewDragHelper.smoothSlideViewTo(contentPane, mWidth, 0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            }
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if (state == ViewDragHelper.STATE_DRAGGING){
                Log.e(TAG, "onViewDragStateChanged: begin dragging");
            }else if (state == ViewDragHelper.STATE_IDLE){
                Log.e(TAG, "onViewDragStateChanged: now is idle");
            }else {
                Log.e(TAG, "onViewDragStateChanged: begin setting");
            }
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
//            Log.e(TAG, "onViewPositionChanged: ");
//            changedView.setScaleY(getViewScale());
//            changedView.setScaleX(getViewScale());
        }

//        @Override
//        public void onViewCaptured(View capturedChild, int activePointerId) {
//            super.onViewCaptured(capturedChild, activePointerId);
//            Log.e(TAG, "onViewCaptured: ");
//            if (capturedChild==contentPane&&contentPane.getLeft()>100){
//                Log.e(TAG, "onViewCaptured: contentPane");
//                mViewDragHelper.smoothSlideViewTo(contentPane, 0, 0);
//                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
//            }
//        }

    };

    public DragViewGroup(@NonNull Context context) {
        super(context);
        initView();
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, mCallback);
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_TOP);
        float density = getResources().getDisplayMetrics().density;
        float density_dpi = getResources().getDisplayMetrics().densityDpi;
//        Log.e(TAG, "initView: density " + density);
//        Log.e(TAG, "initView: density_dpi " + density_dpi);
//        Log.e(TAG, "initView: " + getResources().getDisplayMetrics().scaledDensity);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = slidePane.getMeasuredWidth();
        Log.e(TAG, "onSizeChanged: mWidth="+mWidth);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        slidePane = getChildAt(0);
        contentPane = getChildAt(1);
        Log.e(TAG, "onFinishInflate: slidePane " + slidePane);
        Log.e(TAG, "onFinishInflate: contentPane " + contentPane);
    }

    private float getViewScale(){
        if (scale>0.5f){
            scale = scale-0.01f;
        }else {
            scale = 0.5f;
        }
        return scale;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
