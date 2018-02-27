package com.example.amyas.customwidget.dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * author: amyas
 * Created on 2018/2/27/027.
 */

public class MyLayout extends LinearLayout {
    public static final String TAG = "MyLayout";
    private int count = 1;

    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: MyLayout");
        if (count++ > 5) {
            return false;
        }
        return true;
    }
}
