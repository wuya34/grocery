package com.example.amyas.grocery.scroll.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: Amyas
 * date: 2017/12/8
 */

public class CircleRatio extends View {
    private Paint mCirclePaint;
    private Paint mArcPaint;
    // view 默认大小
    // TODO: 2017/12/8 根据length 按照比例设置draw coordinate
    private int length = 600;
    private int mCircleXY = length/2;
    // 圆弧宽度

    private int arcWidth = 30;
    private float mRatio = 75;
    private RectF mArcRectF1;
    private RectF mArcRectF2;
    private RectF mArcRectF3;
    public CircleRatio(Context context) {
        super(context);
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.YELLOW);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mArcPaint = new Paint();
        mArcPaint.setColor(Color.GREEN);
        mArcPaint.setStyle(Paint.Style.FILL);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStrokeWidth(arcWidth);

        mArcRectF1 = new RectF(
                (float) 0.1*length,
                (float)0.1*length,
                (float)0.9*length,
                (float)0.9*length);
        mArcRectF2 = new RectF(
                (float) 0.2*length,
                (float)0.2*length,
                (float)0.8*length,
                (float)0.8*length);
        mArcRectF3 = new RectF(
                (float) 0.3*length,
                (float)0.3*length,
                (float)0.7*length,
                (float)0.7*length);
    }

    public CircleRatio(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.YELLOW);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mArcPaint = new Paint();
        mArcPaint.setColor(Color.GREEN);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(arcWidth);

        mArcRectF1 = new RectF(
                (float) 0.1*length,
                (float)0.1*length,
                (float)0.9*length,
                (float)0.9*length);
        mArcRectF2 = new RectF(
                (float) 0.2*length,
                (float)0.2*length,
                (float)0.8*length,
                (float)0.8*length);
        mArcRectF3 = new RectF(
                (float) 0.3*length,
                (float)0.3*length,
                (float)0.7*length,
                (float)0.7*length);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawArc1(canvas);
        drawArc2(canvas);
        drawArc3(canvas);

    }
    private void drawArc1(Canvas canvas){
        mArcPaint.setColor(Color.RED);
        canvas.drawArc(mArcRectF1,-90,270,false, mArcPaint);
    }
    private void drawArc2(Canvas canvas){
        mArcPaint.setColor(Color.MAGENTA);
        canvas.drawArc(mArcRectF2,-90,270,false, mArcPaint);
    }
    private void drawArc3(Canvas canvas){
        mArcPaint.setColor(Color.BLUE);
        canvas.drawArc(mArcRectF3,-90,270,false, mArcPaint);
    }

    private void drawCircle(Canvas canvas){
        canvas.drawCircle(mCircleXY, mCircleXY,mRatio, mCirclePaint);
    }
}
