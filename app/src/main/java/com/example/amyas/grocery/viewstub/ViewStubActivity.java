package com.example.amyas.grocery.viewstub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewStub;

import com.example.amyas.grocery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewStubActivity extends AppCompatActivity {

    @BindView(R.id.viewstub_1)
    ViewStub mViewstub1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
        ButterKnife.bind(this);
    }
}
