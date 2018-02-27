package com.example.amyas.customwidget.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amyas.customwidget.R;

import static android.app.Activity.RESULT_FIRST_USER;

/**
 * Created by Administrator on 2018/2/7/007.
 */

public class FirstFragment extends Fragment {
    public static final String TAG = "FirstFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_test,container,false);
        Button button = view.findViewById(R.id.set_result);
        button.setText("start second Activity");
        button.setOnClickListener(view1 -> {
            Intent i = new Intent(getActivity(),SecondActivity.class);
            startActivityForResult(i,FirstActivity.FirstActivityCode);
        });
        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult: requestCode ->"+requestCode+" resultCode ->"+
                resultCode);
    }
}
