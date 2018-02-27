package com.example.amyas.customwidget.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.amyas.customwidget.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Amyas
 * date: 2018/1/12
 */

public class LeakActivity extends AppCompatActivity {
    public static final String TAG = "LeakActivity";

    @BindView(R.id.button21)
    Button mButton21;
    private Unbinder unbinder;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.button21)
    public void onViewClicked() {
        Disposable interval = Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> Log.e(TAG, "onMButton14Clicked: accept->" + aLong));
        mCompositeDisposable.add(interval);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mCompositeDisposable.dispose();
    }
}
