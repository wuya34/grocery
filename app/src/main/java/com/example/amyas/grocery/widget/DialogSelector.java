package com.example.amyas.grocery.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.amyas.grocery.R;

/**
 * author: Amyas
 * date: 2018/2/6
 */

public class DialogSelector extends Dialog {
    private RadioButton mButton1;
    private RadioButton mButton2;
    private int currentRadioButton = 0;
    private onRadioButtonClickListener mListener;
    private Context mContext;

    public DialogSelector(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public DialogSelector(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected DialogSelector(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_selector);
        setCanceledOnTouchOutside(false);
        configWidget();
    }

    public void setOnSelectListener(onRadioButtonClickListener listener) {
        mListener = listener;
    }

    private void configWidget() {
        RelativeLayout relativeLayout1 = findViewById(R.id.cancel_rl);
        RelativeLayout relativeLayout2 = findViewById(R.id.confirm_rl);
        mButton1 = findViewById(R.id.radio1);
        mButton2 = findViewById(R.id.radio2);
        mButton1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                currentRadioButton = 1;
                mButton2.setChecked(false);
            }
        });
        mButton2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                currentRadioButton = 2;
                mButton1.setChecked(false);
            }
        });
        mButton1.setChecked(true);
        relativeLayout1.setOnClickListener(v -> this.dismiss());
        relativeLayout2.setOnClickListener(v -> {
            if (currentRadioButton == 1) {
                mListener.onFirstSelected();
            } else if (currentRadioButton == 2) {
                mListener.onSecondSelected();
            }
        });
    }

    public interface onRadioButtonClickListener {
        // 选择第一个radioButton
        void onFirstSelected();

        // 选择第二个radioButton
        void onSecondSelected();
    }
}
