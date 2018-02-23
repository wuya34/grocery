package com.example.amyas.customwidget.widget;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.amyas.customwidget.R;
import com.example.amyas.customwidget.util.UIUtil;

/**
 * author: Amyas
 * date: 2018/2/23
 */

public class PopupWindowTest extends AppCompatActivity {
    private WhitePopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_test);
        if (popupWindow == null) {
            View popupView = LayoutInflater.from(this).inflate(R.layout.popupwindow_acreage, null);
            // 设置选择亩数监听
            popupView.findViewById(R.id.a).setOnClickListener(v -> {
                UIUtil.showToast(PopupWindowTest.this,"点击了a");
                popupWindow.dismiss();
            });
            popupView.findViewById(R.id.b).setOnClickListener(v -> {
                UIUtil.showToast(PopupWindowTest.this,"点击了b");
                popupWindow.dismiss();
            });
            popupView.findViewById(R.id.c).setOnClickListener(v -> {
                UIUtil.showToast(PopupWindowTest.this,"点击了c");
                popupWindow.dismiss();
            });
            //初始化一个PopupWindow，width和height都是WRAP_CONTENT
            popupWindow = new WhitePopupWindow(popupView,
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(true);
            popupWindow.setClippingEnabled(false);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setOnDismissListener(() -> setBackgroundAlpha(1f));
        }
        setBackgroundAlpha(0.6f);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
        // TODO: 2018/2/23 将以下的Null更换成view
        popupWindow.showAsDropDown(null, 100, 0);
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
}
