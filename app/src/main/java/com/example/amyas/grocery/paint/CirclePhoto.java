package com.example.amyas.grocery.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.amyas.grocery.R;

/**
 * author: Amyas
 * date: 2017/12/18
 */

public class CirclePhoto extends View {

    private Paint mCirclePaint;
    private Paint mLinearGradientPaint;

    public CirclePhoto(Context context) {
        super(context);
    }

    public CirclePhoto(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.camila);

        mCirclePaint = new Paint();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mCirclePaint.setShader(bitmapShader);

        mLinearGradientPaint = new Paint();
        mLinearGradientPaint.setShader(new LinearGradient(0,0,500,0,
                Color.BLUE, Color.YELLOW,Shader.TileMode.REPEAT));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(100,100, 100, mCirclePaint);
        canvas.drawRect(0,300,500,800,mLinearGradientPaint);
    }
}
