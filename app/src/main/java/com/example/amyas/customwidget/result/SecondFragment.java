package com.example.amyas.customwidget.result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amyas.customwidget.R;

/**
 * Created by Administrator on 2018/2/7/007.
 */

public class SecondFragment extends Fragment {

    public static final String TAG = "SecondFragment";
    public static final int SecondFragmentCode = 100;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_test,container,false);
        view.findViewById(R.id.set_result).setOnClickListener(view1 -> {
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().finish();
        });
        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult: requestCode ->"+requestCode+" resultCode ->"+
                resultCode);
    }
}
