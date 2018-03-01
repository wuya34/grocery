package com.example.amyas.grocery.animator;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.amyas.grocery.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: amyas
 * Created on 2018/2/7/007.
 */

public class PropertyAnimator extends AppCompatActivity {
    @BindView(R.id.translation)
    Button translation;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animator);
        ButterKnife.bind(this);
        Intent intent = getParentActivityIntent();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        return super.openOrCreateDatabase(name, mode, factory);
    }

    @OnClick(R.id.translation)
    public void onTranslationClicked() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                translation, "translationX", 300
        );
        animator.setDuration(1000);
        animator.start();
    }
}
