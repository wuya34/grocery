package com.example.amyas.grocery.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.amyas.grocery.R;

/**
 * author: Amyas
 * date: 2017/12/16
 */

public class XfermodeView extends View {

    private Bitmap mBitmap;
    private Bitmap camila;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;



    public XfermodeView(Context context) {
        super(context);
        init();
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化： 画布 画笔
     */
    private void init(){
        mPath = new Path();
        camila = BitmapFactory.decodeResource(getResources(), R.drawable.camila);
        mBitmap = Bitmap.createBitmap(camila.getWidth(), camila.getHeight(), Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(0);
        mPaint.setAntiAlias(true);
        // TODO: 2017/12/16 测试stroke模式
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(50);

        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(camila,0,0, null);
        canvas.drawBitmap(mBitmap, 0 ,0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        Path path = new Path(); // 如果将path定义在此可以有扇形擦除效果
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
        }
        mCanvas.drawPath(mPath, mPaint);
        invalidate();
        return true;
    }
}
