package com.example.amyas.grocery.async.rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author: Amyas
 * date: 2018/3/1
 */

public class BaseObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
