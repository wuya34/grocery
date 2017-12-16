package com.example.amyas.customwidget.activity;

import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * author: Amyas
 * date: 2017/12/15
 */

public class BaseActivity extends AppCompatActivity {
    protected Unbinder mUnbinder;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
