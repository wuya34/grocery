package com.example.amyas.customwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static com.example.amyas.customwidget.activity.MainActivity.TAG;


/**
 * author: Amyas
 * date: 2017/12/7
 */

public class CustomTextView extends AppCompatTextView {
    public static final String TAG = "CustomTextView";
    private Paint mPaint1= new Paint();
    private Paint mPaint2= new Paint();
    private Paint mPaint;
    private int mViewWidth;
    private int mViewHeight;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private int mTranslate;
    private int lastX;
    private int lastY;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: force ondraw");
        mPaint1.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,getMeasuredWidth(), getMeasuredHeight(), mPaint1);
//        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint2);
        canvas.save();
//        canvas.translate(10, 0);
        super.onDraw(canvas);
        canvas.restore();

        if (mMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2*mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mMatrix);
            postInvalidateDelayed(100);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.e(TAG, "onTouchEvent: 落点坐标 "+"("+x+" , "+y+")" );
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent: ACTION_DOWN");
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent: ACTION_MOVE");
                int offsetX = x-lastX;
                int offsetY = y-lastY;
                Log.e(TAG, "onTouchEvent: 移动偏移 "+"("+offsetX+" , "+offsetY+")" );
                layout(getLeft()+offsetX,
                        getTop()+offsetY,
                        getRight()+offsetX,
                        getBottom()+offsetY);

                lastX = x;
                lastY = y;
                Log.e(TAG, "onTouchEvent: last坐标 "+"("+lastX+" , "+lastY+")" );
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: force sizeChanged");
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{Color.BLUE, 0xffffffff, Color.BLUE}, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mMatrix = new Matrix();
            }
        }


    }
}
