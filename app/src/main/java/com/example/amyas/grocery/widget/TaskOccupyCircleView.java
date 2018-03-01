package com.example.amyas.grocery.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.amyas.grocery.R;

/**
 * 个人作业界面的作业占比图
 * author: Amyas
 * date: 2018/1/22
 */

public class TaskOccupyCircleView extends View {
    private int height;
    private int width;
    // 橘黄色， 红色以及绿色所占的弧度 
    // 例如 orangeSweepAngle = 90 表示 橘黄色占整个圆的四分之一
    private int orangeSweepAngle = 90;
    private int redSweepAngle = 5;
    private int greenSweepAngle = 5;
    // 从外到内 弧形的定位矩形
    private RectF mRectF1;
    private RectF mRectF2;
    // private RectF mRectF3;
    private Paint mPaintFill;
    private Paint mPaintStroke;
    // 作业占比显示文本
    private String ratio = "2%";
    private String ratioText = "作业占比";

    public TaskOccupyCircleView(Context context) {
        super(context);
        initView();
    }

    public TaskOccupyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TaskOccupyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public TaskOccupyCircleView setRatio(String ratio) {
        this.ratio = ratio;
        return this;
    }

    public void setSweepAngle(int orangeSweepAngle, int redSweepAngle, int greenSweepAngle) {
        this.orangeSweepAngle = orangeSweepAngle;
        this.redSweepAngle = redSweepAngle;
        this.greenSweepAngle = greenSweepAngle;
        postInvalidate();
    }

    private void initView() {
        mPaintFill = new Paint();
        mPaintFill.setAntiAlias(true);
        mPaintFill.setStyle(Paint.Style.FILL);

        mPaintStroke = new Paint();
        mPaintStroke.setAntiAlias(true);
        mPaintStroke.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        initSize();
    }

    private void initSize() {
        float rectFStartX1 = (float) (0.08 * width);
        float rectFStartY1 = (float) (0.08 * height);
        float rectFEndX1 = (float) (0.92 * width);
        float rectFEndY1 = (float) (0.92 * height);

        float rectFStartX2 = (float) (0.16 * width);
        float rectFStartY2 = (float) (0.16 * height);
        float rectFEndX2 = (float) (0.84 * width);
        float rectFEndY2 = (float) (0.84 * height);

        mRectF1 = new RectF(rectFStartX1, rectFStartY1, rectFEndX1, rectFEndY1);
        mRectF2 = new RectF(rectFStartX2, rectFStartY2, rectFEndX2, rectFEndY2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaintFill.setColor(getResources().getColor(R.color.blue));
        canvas.drawCircle(width / 2, height / 2, (float) (0.33 * width), mPaintFill);
        // 橘黄色圆弧
        mPaintStroke.setColor(getResources().getColor(R.color.wheat_orange));
        mPaintStroke.setStrokeWidth((float) (0.16 * width));
        canvas.drawArc(mRectF1, -90, orangeSweepAngle, false, mPaintStroke);
        // 红色圆弧
        mPaintStroke.setColor(getResources().getColor(R.color.rice_red));
        canvas.drawArc(mRectF1, -90 + orangeSweepAngle, redSweepAngle, false, mPaintStroke);
        // 绿色圆弧
        mPaintStroke.setColor(getResources().getColor(R.color.corn_green));
        canvas.drawArc(mRectF1, -90 + orangeSweepAngle + redSweepAngle, greenSweepAngle, false, mPaintStroke);
        // 分隔线
        mPaintStroke.setColor(getResources().getColor(R.color.gray));
        mPaintStroke.setStrokeWidth((float) (0.01 * width));
        canvas.drawArc(mRectF2, 0, 360, false, mPaintStroke);
        // 圆中文本显示
        mPaintFill.setColor(getResources().getColor(R.color.white));
        mPaintFill.setTextSize(24);
        canvas.drawText(ratioText, (float) (width / 2 - 48), (float) (height / 2 + 17), mPaintFill);
        mPaintFill.setTextSize(28);
        canvas.drawText(ratio, (float) (width / 2 - 18), (float) (height / 2 - 17), mPaintFill);

    }
}
