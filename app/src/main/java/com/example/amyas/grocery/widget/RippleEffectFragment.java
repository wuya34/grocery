package com.example.amyas.grocery.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amyas.grocery.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: Amyas
 * date: 2018/3/6
 */

public class RippleEffectFragment extends Fragment {

    @BindView(R.id.ripple)
    RippleEffectView mRipple;
    Unbinder unbinder;
    private boolean isRunning = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ripple, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ripple)
    public void onViewClicked() {
        if (!isRunning){
            mRipple.startAnimation();
            isRunning = true;
        }else {
            mRipple.stopAnimation();
            isRunning = false;
        }

    }
}
