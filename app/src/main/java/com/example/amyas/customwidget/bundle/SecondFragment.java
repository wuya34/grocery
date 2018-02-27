package com.example.amyas.customwidget.bundle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amyas.customwidget.R;

/**
 * author: Amyas
 * date: 2018/2/9
 */

public class SecondFragment extends Fragment {
    private String extra;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extra = getArguments().getString("extra");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewstub_2, container, false);
        Toast.makeText(getActivity(), "get extra: " + extra, Toast.LENGTH_SHORT).show();
        return view;
    }
}
