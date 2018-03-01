package com.example.amyas.grocery.async.rxjava;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.amyas.grocery.R;
import com.example.amyas.grocery.retrofit.Api;
import com.example.amyas.grocery.retrofit.RetrofitService;
import com.example.amyas.grocery.util.RxUtil;
import com.example.amyas.grocery.util.UIUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author: Amyas
 * date: 2018/1/12
 */

public class BlankActivity extends AppCompatActivity {

    @BindView(R.id.button21)
    Button mButton21;
    private Unbinder unbinder;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        unbinder = ButterKnife.bind(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @OnClick(R.id.button21)
    public void onViewClicked() {
        Intent intent = new Intent(this, LeakActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mCompositeDisposable.dispose();
    }

    @OnClick(R.id.button22)
    public void onErrorHandlerClicked() {
        RetrofitService.getInstance().mApi.getLandList(Api.token,null,1,10)
                .map(new RxUtil.ServerResponseFunc<>())
                .compose(RxUtil.workIoObMain())
                .onErrorResumeNext(new RxUtil.HttpResponseFunc<>())
                .subscribe(new Observer<List<LandResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<LandResponse> landResponses) {
                        Log.d("11", "onNext: "+landResponses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        UIUtil.showToast(BlankActivity.this, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("11", "onComplete: ");
                    }
                });

    }

}
