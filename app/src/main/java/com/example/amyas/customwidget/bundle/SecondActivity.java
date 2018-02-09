package com.example.amyas.customwidget.bundle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.amyas.customwidget.R;
import com.example.amyas.customwidget.util.ActivityUtil;

/**
 * author: Amyas
 * date: 2018/2/9
 */

public class SecondActivity extends AppCompatActivity {
    private SecondFragment mFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstub_2);
        mFragment = new SecondFragment();
//        String extra = getIntent().getStringExtra("extra");
        Bundle extra = getIntent().getBundleExtra("extra");
        Toast.makeText(this,"get extra: "+extra,Toast.LENGTH_SHORT).show();
//        Bundle bundle = new Bundle();
//        bundle.putString("extra",extra);
        mFragment.setArguments(extra);
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),
                mFragment,R.id.fragment_container);
    }
}
