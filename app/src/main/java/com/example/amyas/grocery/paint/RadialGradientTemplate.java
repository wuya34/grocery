package com.example.amyas.grocery.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: Amyas
 * date: 2017/12/19
 */

public class RadialGradientTemplate extends View {

    private RadialGradient mGradient;
    private Paint mPaint;
    private Paint mPaint1;



    public RadialGradientTemplate(Context context) {
        super(context);
        initView();
    }

    public RadialGradientTemplate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        mGradient = new RadialGradient(400,400,300,
                Color.RED, Color.TRANSPARENT, Shader.TileMode.REPEAT);
        mPaint = new Paint();
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setShader(mGradient);
        mPaint1 = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(400,400,400, mPaint);
    }
}
