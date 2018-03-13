package com.example.amyas.grocery.result;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.amyas.grocery.R;
import com.example.amyas.grocery.util.ActivityUtil;

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
        Log.e(TAG, "onCreate: FirstActivity");
        setContentView(R.layout.activity_result_test);
        fragment = new FirstFragment();
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),
                fragment, R.id.fragment_container);

        String s = Looper.myLooper().toString();
        Log.d(TAG, "onCreate: FirstActivity loop ->" + s );


        WindowManager wm = (WindowManager) getSystemService(getApplication().WINDOW_SERVICE);
        View view = LayoutInflater.from(getApplication()).inflate(R.layout.activity_blank,null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int flags = intent.getFlags();
        int a =  Intent.FLAG_ACTIVITY_CLEAR_TOP;
        int b = Intent.FLAG_ACTIVITY_SINGLE_TOP;
        int c = Intent.FLAG_RECEIVER_FOREGROUND;

        if (flags==a){
            Log.e(TAG, "onNewIntent: FirstActivity flags is clear top -> "+flags );
        }else if (flags == b){
            Log.e(TAG, "onNewIntent: FirstActivity flags is single top -> "+flags );
        }else if (flags==c){
            Log.e(TAG, "onNewIntent: FirstActivity flags is FLAG_RECEIVER_FOREGROUND-> "+flags );
        }else {
            Log.e(TAG, "onNewIntent: FirstActivity flags is -> "+flags );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: requestCode ->"+requestCode+" resultCode ->"+
        resultCode);
        fragment.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: FirstActivity" );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: FirstActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: FirstActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: FirstActivity");
    }

}
