package com.example.amyas.customwidget.dispatch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.amyas.customwidget.R;
import com.example.amyas.customwidget.util.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: amyas
 * Created on 2018/2/27/027.
 */

public class TestDispatchActivity extends AppCompatActivity {
    public static final String TAG = "TestDispatchActivity";
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    Unbinder unbinder;
    @BindView(R.id.myLayout)
    MyLayout myLayout;
    @BindView(R.id.button3)
    Button button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_test);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    public void onButton1Clicked() {
        UIUtil.showToast(this, "onButton1Clicked");
    }

    @OnClick(R.id.button2)
    public void onButton2Clicked() {
        UIUtil.showToast(this, "onButton2Clicked");
    }

    @OnClick(R.id.button3)
    public void onButton3Clicked() {
        // 禁止拦截事件
        myLayout.requestDisallowInterceptTouchEvent(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
