package com.example.amyas.grocery.activity;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.example.amyas.grocery.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main10Activity extends AppCompatActivity {

    @BindView(R.id.oval)
    ImageView mOval;
    @BindView(R.id.rectangle)
    ImageView mRectangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        ButterKnife.bind(this);

//        RemoteViews contentView=

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.oval)
    public void onMOvalClicked() {
        Log.e("Main10Activity", "onMOvalClicked: ");
        Animator animator = ViewAnimationUtils.createCircularReveal(mOval,
                mOval.getWidth()/2, mOval.getHeight()/2,
                mOval.getWidth(),0);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(500);
        animator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.rectangle)
    public void onMRectangleClicked() {
        Log.e("Main10Activity", "onMRectangleClicked: ");
        Animator animator = ViewAnimationUtils.createCircularReveal(mRectangle,
                0, 0,
                0, (float) Math.hypot(mRectangle.getWidth(), mRectangle.getHeight()));
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(500);
        animator.start();
    }
}
