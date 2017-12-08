package com.example.amyas.customwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TopBar topBar = findViewById(R.id.top_bar);
//        topBar.setOnTopBarClickLIstener(new TopBar.topBarClickListener() {
//            @Override
//            public void leftClick() {
//                ToastUtil.showToast(MainActivity.this, "left button");
//            }
//
//            @Override
//            public void rightClick() {
//                ToastUtil.showToast(MainActivity.this, "right button");
//            }
//        });
    }
}
