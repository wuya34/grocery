package com.example.amyas.customwidget.async.rxjava;

import android.view.View;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * author: Amyas
 * date: 2018/1/11
 */

public class ViewClickOnSubscribe extends Observable<Object> {
    private View mView;

    public ViewClickOnSubscribe(View view) {
        mView = view;
    }

    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
        mView.setOnClickListener(v -> observer.onNext(1));
    }

}
