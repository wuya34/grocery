package com.example.amyas.customwidget.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.amyas.customwidget.R;

/**
 * author: Amyas
 * date: 2017/12/18
 */

public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean isDrawing;
    private int x = 0;
    private int y = 0;
    private Path mPath;
    private Paint mPaint;


    public SurfaceViewTemplate(Context context) {
        super(context);
        init();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.camila);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas  = new Canvas(bitmap1);
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing = true;
        mPath.moveTo(0,0);
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }


    @Override
    public void run() {
        while (isDrawing) {
            Log.e("111", "run:  on running");
            draw();
            x += 1;
            y = (int) (100 * Math.sin(2 * x * Math.PI / 180) + 400);
            Log.e("111", "run: ("+x+","+y+")");
            mPath.lineTo(x, y);

        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
            Log.e("111", "draw:  on drawing");
            Thread.sleep(100);
        } catch (Exception c) {

        } finally {
            if (mCanvas != null) {

                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
