package com.example.amyas.grocery.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amyas.grocery.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Amyas
 * date: 2018/3/5
 */

public class SingleOccupyCircleFragment extends Fragment {

    @BindView(R.id.single)
    SingleOccupyCircleView mSingle;
    @BindView(R.id.task)
    TaskOccupyCircleView mTask;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.activity_occupy_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        inflateWidget();
        return view;
    }

    private void inflateWidget() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_corn);
        mSingle.setArguments(bitmap, getResources().getColor(R.color.wheat_orange), 160);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
