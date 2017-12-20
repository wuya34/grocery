package com.example.amyas.customwidget.scroll.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * author: Amyas
 * date: 2017/12/8
 */

public class MusicHeartBeat extends View {
    private Paint mPaint;
    // 整个view长宽
    private int width = 800;
    private int height = 500;
    private int mRectNumber = 12;
    private int perWidth = width/(mRectNumber+1);
    // 单条rect高度
    private int currentHeight = 0;
    private int offset = 3;
    private double mRandom;
    private int mRectHeight;
    private LinearGradient mLinearGradient;


    public MusicHeartBeat(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println(" force ondraw---------");
        drawMusicHeartBeat(canvas);
    }

    private void drawMusicHeartBeat(Canvas canvas) {
        for (int i = 0; i < 12; i++) {
            mRandom = Math.random();
            currentHeight = (int)(mRandom*height);
            canvas.drawRect(
                    perWidth*i+offset,
                    currentHeight,
                    perWidth*(i+1),
                    height,
                    mPaint
                    );
        }
        postInvalidateDelayed(800);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println(" size changed---------");
        mRectHeight = getHeight();
        mLinearGradient = new LinearGradient(
                0,0, perWidth, mRectHeight, Color.RED,Color.BLUE,
                Shader.TileMode.CLAMP
        );
        mPaint.setShader(mLinearGradient);
    }
}
