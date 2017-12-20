package com.example.amyas.customwidget.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.amyas.customwidget.R;

/**
 * author: Amyas
 * date: 2017/12/18
 */

public class ShadowPhoto extends View {
    private Bitmap mBitmap;
    private Bitmap mShadowBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;
    private int height;

    public ShadowPhoto(Context context) {
        super(context);
        init();
    }

    public ShadowPhoto(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.camila);
        Matrix matrix = new Matrix();
        matrix.setScale(1f,-1f);
        mShadowBitmap = Bitmap.createBitmap(mBitmap,0,0, mBitmap.getWidth(),
                mBitmap.getHeight(),matrix,true);
        mPaint = new Paint();
        height = mBitmap.getHeight();
        mPaint.setShader(new LinearGradient(0, (float) (height),0, (float) (1.25*height),0XDD000000,
                0X10000000, Shader.TileMode.CLAMP));
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mBitmap,0,0,null);
        canvas.drawBitmap(mShadowBitmap, 0, mBitmap.getHeight(), null);
        mPaint.setXfermode(mXfermode);
        canvas.drawRect(0,height, mBitmap.getWidth(),2*height,mPaint);
        mPaint.setXfermode(null);
    }
}
