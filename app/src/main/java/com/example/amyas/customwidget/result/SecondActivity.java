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

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_test);
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),
                new SecondFragment(), R.id.fragment_container);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult: requestCode ->" + requestCode + " resultCode ->" +
                resultCode);
    }
}
