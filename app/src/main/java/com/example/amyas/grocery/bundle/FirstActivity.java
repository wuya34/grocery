package com.example.amyas.grocery.bundle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.amyas.grocery.R;

/**
 * author: Amyas
 * date: 2018/2/9
 */

public class FirstActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstub_2);
        Intent intent = new Intent(this,SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("extra","extra");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
