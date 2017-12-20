package com.example.amyas.customwidget.activity;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.example.amyas.customwidget.R;
import com.example.amyas.customwidget.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main7Activity extends AppCompatActivity {

    @BindView(R.id.rectangle)
    ImageView mRectangle;
    @BindView(R.id.start_animation)
    Button mStartAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_animation)
    public void startAnim() {
        ((Animatable) mRectangle.getDrawable()).start();
        //        CustomTV customTV = new CustomTV();
        //        mRectangle.startAnimation(customTV);
        //        float camilaTop = mRectangle.getTop();
        //        ObjectAnimator animator = ObjectAnimator.ofFloat(mRectangle, "y", camilaTop,500)
        //                .setDuration(1000);
        //        animator.setInterpolator(new OvershootInterpolator());
        //        animator.start();
    }

    @OnClick(R.id.rectangle)
    public void camilaClick() {
        ToastUtil.showToast(Main7Activity.this, "clicked camila..");
    }


}
