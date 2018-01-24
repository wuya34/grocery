package com.example.amyas.customwidget.paint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.amyas.customwidget.R;

public class PaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        IDCard idCard = findViewById(R.id.idCard);
        idCard.setArg1("amyas")
                .setArg2("362426199246545")
                .setArg3("HDM100026")
                .setArg4("已通过");
    }
}
