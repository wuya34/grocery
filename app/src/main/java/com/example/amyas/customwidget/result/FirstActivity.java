package com.example.amyas.customwidget.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.amyas.customwidget.R;
import com.example.amyas.customwidget.util.ActivityUtil;

/**
 * Created by Administrator on 2018/2/7/007.
 */

public class FirstActivity extends AppCompatActivity {
    public static final String TAG  = "FirstActivity";
    public static final int FirstActivityCode = 99;
    private FirstFragment fragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_test);
        fragment = new FirstFragment();
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),
                fragment, R.id.fragment_container);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: requestCode ->"+requestCode+" resultCode ->"+
        resultCode);
        fragment.onActivityResult(requestCode,resultCode,data);
    }
}
