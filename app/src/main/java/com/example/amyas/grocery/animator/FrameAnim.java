package com.example.amyas.grocery.animator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.example.amyas.grocery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Amyas
 * date: 2018/3/2
 */

public class FrameAnim extends AppCompatActivity {

    @BindView(R.id.button25)
    Button mButton25;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        ButterKnife.bind(this);
        startAnim();
    }

    private void startAnim() {
        Animation animation = createAnim();
        mButton25.startAnimation(animation);
    }

    private Animation createAnim() {
        TranslateAnimation animation = new TranslateAnimation(0,300,0,300);
//        animation.setRepeatMode(Animation.RESTART);
//        animation.setRepeatCount(3);
        animation.setDuration(3000);
//        animation.setStartOffset(500);
        return animation;
    }
}
