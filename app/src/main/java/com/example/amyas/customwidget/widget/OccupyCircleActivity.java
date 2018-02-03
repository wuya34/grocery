package com.example.amyas.customwidget.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.amyas.customwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OccupyCircleActivity extends AppCompatActivity {

    @BindView(R.id.single)
    SingleOccupyCircleView mSingle;
    @BindView(R.id.task)
    TaskOccupyCircleView mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occupy_circle);
        ButterKnife.bind(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_corn);
        mSingle.setArguments(bitmap,getResources().getColor(R.color.dark_blue),150);
        mTask.setRatio("20%").setSweepAngle(60,60,60);
    }
}
