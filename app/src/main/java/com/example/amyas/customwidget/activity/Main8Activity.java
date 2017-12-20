package com.example.amyas.customwidget.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amyas.customwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main8Activity extends AppCompatActivity {

    @BindView(R.id.moon)
    ImageView mMoon;
    @BindView(R.id.place)
    ImageView mPlace;
    @BindView(R.id.bath)
    ImageView mBath;
    @BindView(R.id.call)
    ImageView mCall;
    @BindView(R.id.spot)
    ImageView mSpot;
    @BindView(R.id.count_down)
    TextView mCountDown;

    private boolean isClicked = false;
    private int process;
    private int emitDistance = 100;
    private ValueAnimator mValueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        ButterKnife.bind(this);

        mValueAnimator = ValueAnimator.ofInt(0, 100);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                process = (int) animation.getAnimatedValue();
                mCountDown.setText(process+" %");
            }
        });
        mValueAnimator.setDuration(10000);
    }

    @OnClick(R.id.spot)
    public void startAnim() {
        if (isClicked) {
            ObjectAnimator spotAnimEnd = ObjectAnimator.ofFloat(mSpot, "alpha", 0.5f, 1f)
                    .setDuration(500);

            ObjectAnimator moonAnimEnd = ObjectAnimator.ofFloat(mMoon, "translationX", -emitDistance, 0)
                    .setDuration(500);

            ObjectAnimator bathAnimEnd = ObjectAnimator.ofFloat(mBath, "translationX", emitDistance, 0)
                    .setDuration(500);

            ObjectAnimator callAnimEnd = ObjectAnimator.ofFloat(mCall, "translationY", emitDistance, 0)
                    .setDuration(500);

            ObjectAnimator placeAnimEnd = ObjectAnimator.ofFloat(mPlace, "translationY", -emitDistance, 0)
                    .setDuration(500);

            AnimatorSet set = new AnimatorSet();
            set.setDuration(500);
            set.setInterpolator(new DecelerateInterpolator());
            set.playTogether(spotAnimEnd, moonAnimEnd, bathAnimEnd, callAnimEnd, placeAnimEnd);
            set.start();
            isClicked = false;
        } else {
            ObjectAnimator spotAnimStart = ObjectAnimator.ofFloat(mSpot, "alpha", 1f, 0.5f)
                    .setDuration(500);

            ObjectAnimator moonAnimStart = ObjectAnimator.ofFloat(mMoon, "translationX", -emitDistance)
                    .setDuration(500);

            ObjectAnimator bathAnimStart = ObjectAnimator.ofFloat(mBath, "translationX", emitDistance)
                    .setDuration(500);

            ObjectAnimator callAnimStart = ObjectAnimator.ofFloat(mCall, "translationY", emitDistance)
                    .setDuration(500);

            ObjectAnimator placeAnimStart = ObjectAnimator.ofFloat(mPlace, "translationY", -emitDistance)
                    .setDuration(500);

            AnimatorSet set = new AnimatorSet();
            set.setDuration(500);
            set.setInterpolator(new BounceInterpolator());
            set.playTogether(spotAnimStart, moonAnimStart, bathAnimStart, callAnimStart, placeAnimStart);
            set.start();

            isClicked = true;
        }

    }

    @OnClick(R.id.moon)
    public void timer() {
        mValueAnimator.start();
    }


}
