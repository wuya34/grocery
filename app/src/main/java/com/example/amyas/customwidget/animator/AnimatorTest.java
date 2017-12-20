package com.example.amyas.customwidget.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: Amyas
 * date: 2017/12/19
 */

public class AnimatorTest extends View{


    public AnimatorTest(Context context) {
        super(context);
        initView();
    }

    public AnimatorTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){

        ObjectAnimator animator = ObjectAnimator.ofInt(this,
                "backgroundColor", Color.BLUE, Color.YELLOW, Color.RED);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private static class WrapperView{
        private View mTarget;
        
        public WrapperView(View target){
            mTarget = target;
            
        }
    }
}
