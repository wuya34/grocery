package com.example.amyas.grocery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * author: Amyas
 * date: 2018/3/6
 */

public class JNIActivity extends AppCompatActivity {
    public static final String TAG = "JNIActivity";
    public static native String helloJni();
    public static native int addCalc(int a, int b);
    static {
        System.loadLibrary("hello_jni");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String hello = helloJni();
        Log.e(TAG, "onCreate: greet from jni: "+hello);
        int result = addCalc(3,4);
        Log.e(TAG, "onCreate: calculate by jni: (3 + 4) = "+result);
    }
}
