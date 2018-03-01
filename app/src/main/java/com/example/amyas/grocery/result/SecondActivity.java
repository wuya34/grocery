package com.example.amyas.grocery.result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.amyas.grocery.R;
import com.example.amyas.grocery.util.ActivityUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/2/7/007.
 */

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_test);
        ButterKnife.bind(this);
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),
                new SecondFragment(), R.id.fragment_container);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult: requestCode ->" + requestCode + " resultCode ->" +
                resultCode);
    }


    @OnClick(R.id.button24)
    public void onViewClicked() {
        setResult(Activity.RESULT_OK);
        onBackPressed();
    }
}
