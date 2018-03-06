package com.example.amyas.grocery.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.amyas.grocery.R;
import com.example.amyas.grocery.util.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WidgetActivity extends AppCompatActivity {


    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_1);
        ButterKnife.bind(this);
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),
                new RippleEffectFragment(), R.id.fragment_container);

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        DialogSelector dialogSelector = new DialogSelector(WidgetActivity.this,R.style.MyDialog);
        dialogSelector.setOnSelectListener(new DialogSelector.onRadioButtonClickListener() {
            @Override
            public void onFirstSelected() {
                Toast.makeText(WidgetActivity.this,"选择方式一", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSecondSelected() {
                Toast.makeText(WidgetActivity.this,"选择方式二", Toast.LENGTH_SHORT).show();
                dialogSelector.dismiss();
            }
        });
        dialogSelector.show();
    }
}
