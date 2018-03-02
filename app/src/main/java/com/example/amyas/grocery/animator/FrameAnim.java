package com.example.amyas.grocery.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
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
    @BindView(R.id.canvas)
    TextView mCanvas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        ButterKnife.bind(this);
        startAnim();
        mCanvas.setOnClickListener(v -> {
            AnimatorSet set = new AnimatorSet();
            set.setDuration(2000)
                    .playTogether(createCanvasTextColorAnimator(),
                    createCanvasYAnimator());
            set.start();
        });

        mButton25.setOnClickListener(v -> {
            AnimatorSet set = (AnimatorSet)
                    AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.text_view_combine);
            set.setTarget(mButton25);
            set.start();
    });
    }

    private void startAnim() {
//        Animation animation = createButtonAnim();
//        createViewAnim().start();
    }

    private ObjectAnimator createCanvasTextColorAnimator(){
        ObjectAnimator animator =
                ObjectAnimator.ofInt(mCanvas, "textColor", Color.BLACK,Color.RED);
        return animator;
    }

    private ObjectAnimator createCanvasYAnimator(){
        ObjectAnimator animator =
                ObjectAnimator.ofFloat(mCanvas, "Y", 200,50,200);
        return animator;
    }

    private Animator createViewAnim(){
        ValueAnimator colorfulCanvas = ValueAnimator.ofFloat(0, 100);
        colorfulCanvas.setDuration(3000);
        colorfulCanvas.setInterpolator(new AccelerateInterpolator());
//        colorfulCanvas.setRepeatCount(ValueAnimator.INFINITE);
        colorfulCanvas.addUpdateListener(animation -> {
            float currentValue = (float) animation.getAnimatedValue();
            Log.d("1", "createViewAnim: currentValue = "+currentValue);
            if (currentValue<33){
                mCanvas.setBackgroundColor(Color.BLUE);
            }else if (currentValue<66){
                mCanvas.setBackgroundColor(Color.YELLOW);
            }else {
                mCanvas.setBackgroundColor(Color.RED);
            }
//            switch (currentValue){
//                case 1:
//                    mCanvas.setBackgroundColor(Color.BLUE);
//                    break;
//                case 2:
//                    mCanvas.setBackgroundColor(Color.YELLOW);
//                    break;
//                case 3:
//                    mCanvas.setBackgroundColor(Color.RED);
//                    break;
//                default:
//                    mCanvas.setBackgroundColor(Color.WHITE);
//            }
        });
        return colorfulCanvas;
    }

    private Animation createButtonAnim() {
        TranslateAnimation animation = new TranslateAnimation(0, 300, 0, 300);
        //        animation.setRepeatMode(Animation.RESTART);
        //        animation.setRepeatCount(3);

        animation.setDuration(3000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //        animation.setStartOffset(500);
        return animation;
    }
}
