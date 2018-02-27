package com.example.amyas.customwidget.animator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.example.amyas.customwidget.R;

import java.lang.annotation.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: amyas
 * Created on 2018/2/7/007.
 */

public class SimpleAnimation extends AppCompatActivity {

    @BindView(R.id.alpha)
    Button alpha;
    @BindView(R.id.rotate)
    Button rotate;
    @BindView(R.id.scale)
    Button scale;
    @BindView(R.id.translate)
    Button translate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_animation);
        ButterKnife.bind(this);
        AnimationSet animationSet = new AnimationSet(true);

    }

    @OnClick(R.id.alpha)
    public void onAlphaClicked() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(1000);
        alpha.startAnimation(animation);
    }

    @OnClick(R.id.rotate)
    public void onRotateClicked() {
        RotateAnimation animation = new RotateAnimation(
                0, 360, RotateAnimation.RELATIVE_TO_SELF,
                0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        rotate.startAnimation(animation);
    }

    @OnClick(R.id.scale)
    public void onScaleClicked() {
        ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1);
        animation.setDuration(1000);
        scale.startAnimation(animation);
    }

    @OnClick(R.id.translate)
    public void onTranslateClicked() {
//        TranslateAnimation animation = new TranslateAnimation(
//                TranslateAnimation.RELATIVE_TO_SELF,0,
//                TranslateAnimation.RELATIVE_TO_SELF,1,
//                TranslateAnimation.RELATIVE_TO_SELF,0,
//                TranslateAnimation.RELATIVE_TO_SELF,0
//        );
//        animation.setDuration(1000);
//        translate.startAnimation(animation);
        translate.animate()
                .rotationY(360)
                .setDuration(1000)
                .start();
    }
}
