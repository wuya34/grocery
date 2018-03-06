package com.example.amyas.grocery.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.amyas.grocery.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Amyas
 * date: 2018/3/6
 */

public class RippleEffectView extends RelativeLayout {
    public static final int DEFAULT_TYPE = 0;
    private int RIPPLE_COUNT = 6;
    private int ripple_color;
    private int ripple_radius = 64;
    private int duration = 3000;
    private float ripple_scale = 6.0f;
    private boolean isRunning = false;
    private int ripple_type = 0;
    private Paint mPaint;
    private float rippleStrokeWidth;
    private AnimatorSet set;
    private List<RippleView> mRippleViewList;
    private List<Animator> animatorList;

    public RippleEffectView(Context context) {
        super(context);
    }

    public RippleEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public RippleEffectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleEffectView);
        rippleStrokeWidth = typedArray.getDimension(R.styleable.RippleEffectView_ripple_width, getResources().getDimension(R.dimen.ripple_stroke_with));
        ripple_color = typedArray.getColor(R.styleable.RippleEffectView_ripple_color, getResources().getColor(R.color.blue));
        RIPPLE_COUNT = typedArray.getInt(R.styleable.RippleEffectView_ripple_count, RIPPLE_COUNT);
        ripple_radius = typedArray.getInt(R.styleable.RippleEffectView_ripple_radius, ripple_radius);
        duration = typedArray.getInt(R.styleable.RippleEffectView_duration, duration);
        ripple_type = typedArray.getInt(R.styleable.RippleEffectView_ripple_type, DEFAULT_TYPE);
        ripple_scale = typedArray.getFloat(R.styleable.RippleEffectView_ripple_scale, ripple_scale);
        typedArray.recycle();

        int rippleDelay = duration / RIPPLE_COUNT;
        mRippleViewList = new ArrayList<>();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(ripple_color);
        if (ripple_type == DEFAULT_TYPE) {
            rippleStrokeWidth = 0;
            mPaint.setStyle(Paint.Style.FILL);
        } else {
            mPaint.setStyle(Paint.Style.STROKE);
        }
        mPaint.setStrokeWidth(rippleStrokeWidth);

        LayoutParams layoutParams = new LayoutParams((int) (2*(ripple_radius + rippleStrokeWidth))
                , (int) (2*(ripple_radius + rippleStrokeWidth)));
        layoutParams.addRule(CENTER_IN_PARENT);

//        ImageView imageView = new ImageView(context);
//        imageView.setLayoutParams(layoutParams);
//        imageView.setImageDrawable(getResources().getDrawable(R.drawable.crop_wheat));
//        addView(imageView);

        animatorList = new ArrayList<>();

        for (int i = 0; i < RIPPLE_COUNT; i++) {
            RippleView view = new RippleView(context);
            addView(view, layoutParams);
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "ScaleX", ripple_scale);
            scaleX.setDuration(duration);
            scaleX.setRepeatMode(ValueAnimator.RESTART);
            scaleX.setRepeatCount(ValueAnimator.INFINITE);
            scaleX.setStartDelay(i * rippleDelay);

            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "ScaleY", ripple_scale);
            scaleY.setDuration(duration);
            scaleY.setRepeatMode(ValueAnimator.RESTART);
            scaleY.setRepeatCount(ValueAnimator.INFINITE);
            scaleY.setStartDelay(i * rippleDelay);

            ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f);
            alpha.setDuration(duration);
            alpha.setRepeatMode(ValueAnimator.RESTART);
            alpha.setRepeatCount(ValueAnimator.INFINITE);
            alpha.setStartDelay(i * rippleDelay);

            animatorList.add(scaleX);
            animatorList.add(scaleY);
            animatorList.add(alpha);
            mRippleViewList.add(view);
        }

        set = new AnimatorSet();
        set.playTogether(animatorList);

    }

    public void startAnimation() {
        if (!isAnimationRunning()) {
            for (RippleView view : mRippleViewList) {
                view.setVisibility(VISIBLE);
            }
            set.start();

            setAnimationRunning(true);
        }
    }

    public void stopAnimation() {
        if (isAnimationRunning()) {
            for (RippleView view : mRippleViewList) {
                view.setVisibility(INVISIBLE);
            }
            set.end();
            setAnimationRunning(false);
        }
    }

    public boolean isAnimationRunning() {
        return isRunning;
    }

    private void setAnimationRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    class RippleView extends View {

        public RippleView(Context context) {
            super(context);
            this.setVisibility(INVISIBLE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int radius = Math.min(getWidth(), getHeight()) / 2;
            canvas.drawCircle(radius, radius, radius - rippleStrokeWidth, mPaint);
        }
    }
}
