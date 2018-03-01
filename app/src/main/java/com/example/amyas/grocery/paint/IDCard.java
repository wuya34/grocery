package com.example.amyas.grocery.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.amyas.grocery.R;

/**
 * 渐变颜色卡片
 * author: Amyas
 * date: 2018/1/22
 */

public class IDCard extends View {
    private int height;
    private int width;

    private String field1 = "飞手姓名:";
    private String arg1 = "";
    private String field2 = "证件号码:";
    private String arg2 = "";
    private String field3 = "飞行编号:";
    private String arg3 = "";
    private String field4 = "认证结果:";
    private String arg4 = "";
    private String arg5 = "恭喜你，飞手审核认证通过~";

    private Paint mCardPaint;
    private Paint mFieldPaint;
    private Paint mArgPaint;
    private Paint mJudgePaint;


    private LinearGradient mLinearGradient;

    public IDCard(Context context) {
        super(context);
        init();
    }

    public IDCard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public IDCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 此处获取attrs参数
     * 初始化画笔
     */
    private void init() {
        // 卡片背景画笔
        mCardPaint = new Paint();
        // 字段画笔
        mFieldPaint = new Paint();
        mFieldPaint.setColor(Color.WHITE);
        mFieldPaint.setAntiAlias(false);
        mFieldPaint.setTextSize(28);
        mFieldPaint.setStyle(Paint.Style.FILL);
        // 参数画笔
        mArgPaint = new Paint();
        mArgPaint.setColor(Color.WHITE);
        mArgPaint.setAntiAlias(true);
        mArgPaint.setTextSize(26);
        mArgPaint.setStyle(Paint.Style.FILL);
        // arg5 画笔
        mJudgePaint = new Paint();
        mJudgePaint.setColor(Color.WHITE);
        mJudgePaint.setAntiAlias(true);
        mJudgePaint.setTextSize(30);
        mJudgePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //        Log.e("IDCard", "onSizeChanged: width,height = ("+width+","+height+")"  );
        mLinearGradient = new LinearGradient(0, height / 2, width, height / 2,
                getResources().getColor(R.color.light_blue),
                getResources().getColor(R.color.dark_blue),
                Shader.TileMode.REPEAT);
        mCardPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int fieldStartX = width / 20;
        int argStartX = (int)(width*0.25);
        int argStartY = (int) (height*0.13);
        int argDivideY = (int)(0.17*height);
        int argLastY = (int) (height*0.85);
        // 背景
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Log.e("IDCard", "onDraw: width,height = (" + width + "," + height + ")");
            canvas.drawRoundRect(0,0,width,height,15,15,mCardPaint);
        }else {
            canvas.drawRect(0,0,width,height,mCardPaint);
        }
        // 字段和参数
        canvas.drawText(field1, fieldStartX, argStartY, mFieldPaint);
        canvas.drawText(arg1, argStartX, argStartY, mArgPaint);
        canvas.drawText(field2, fieldStartX, argStartY + argDivideY, mFieldPaint);
        canvas.drawText(arg2, argStartX, argStartY + argDivideY, mArgPaint);
        canvas.drawText(field3, fieldStartX, argStartY + 2*argDivideY, mFieldPaint);
        canvas.drawText(arg3, argStartX, argStartY + 2*argDivideY, mArgPaint);
        canvas.drawText(field4, fieldStartX, argStartY + 3*argDivideY, mFieldPaint);
        canvas.drawText(arg4, argStartX, argStartY + 3*argDivideY, mArgPaint);
        canvas.drawText(arg5, fieldStartX, argLastY, mJudgePaint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        //        Log.e("IDCard", "onMeasure: width,height = ("+width+","+height+")");

    }

    public IDCard setField1(String field1) {
        this.field1 = field1;
        return this;
    }

    public IDCard setArg1(String arg1) {
        this.arg1 = arg1;
        return this;
    }

    public IDCard setField2(String field2) {
        this.field2 = field2;
        return this;
    }

    public IDCard setArg2(String arg2) {
        this.arg2 = arg2;
        return this;
    }

    public IDCard setField3(String field3) {
        this.field3 = field3;
        return this;
    }

    public IDCard setArg3(String arg3) {
        this.arg3 = arg3;
        return this;
    }

    public IDCard setField4(String field4) {
        this.field4 = field4;
        return this;
    }

    public IDCard setArg4(String arg4) {
        this.arg4 = arg4;
        return this;
    }

    public IDCard setArg5(String arg5) {
        this.arg5 = arg5;
        return this;
    }
}
