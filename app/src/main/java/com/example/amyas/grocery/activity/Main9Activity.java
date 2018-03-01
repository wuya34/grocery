package com.example.amyas.grocery.activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.amyas.grocery.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main9Activity extends AppCompatActivity {
    @BindView(R.id.explore)
    Button mExplore;
    @BindView(R.id.slide)
    Button mSlide;
    @BindView(R.id.fade)
    Button mFade;
    @BindView(R.id.share)
    Button mShare;
    @BindView(R.id.fab_button)
    View mFabButton;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        ButterKnife.bind(this);

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.explore, R.id.slide, R.id.fade, R.id.share})
    public void onViewClicked(View view) {
        mIntent = new Intent(this, AnimateActivity.class);
        switch (view.getId()) {
            case R.id.explore:
                mIntent.putExtra("flag", 0);
                startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.slide:
                mIntent.putExtra("flag", 1);
                startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.fade:
                mIntent.putExtra("flag", 2);
                startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.share:
                mIntent.putExtra("flag", 3);
                startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(
                        this, new Pair[]{Pair.create(view, "share"), Pair.create(mFabButton, "fab")}
                ).toBundle());
                break;
        }
    }
}
