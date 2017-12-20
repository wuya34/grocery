package com.example.amyas.customwidget.animator;

import android.graphics.Matrix;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * author: Amyas
 * date: 2017/12/19
 */

public class CustomTV extends Animation {
    private float mWidth;
    private float mHeight;
    private double a;

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix = t.getMatrix();
        // TODO: 2017/12/19
        a = Math.log10(interpolatedTime)/Math.log10(0.64);
        if (interpolatedTime < 0.8) {
            matrix.preScale(1, (float) a, mWidth / 2, mHeight / 2);
        }
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mWidth = width;
        mHeight = height;
        setDuration(1000);
        setFillAfter(true);
        setInterpolator(new AccelerateInterpolator());
    }

    public static float transformX(float x){
        return 1/(x*x*x*x*x);
    }
}
