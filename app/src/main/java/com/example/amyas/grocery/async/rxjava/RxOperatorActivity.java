package com.example.amyas.grocery.async.rxjava;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.util.Log;
import android.view.Window;
import android.widget.Button;

import com.example.amyas.grocery.R;
import com.example.amyas.grocery.util.RxUtil;
import com.example.amyas.grocery.util.UIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Rxjava api 使用以及自定义viewgroup控件 DragViewGroup
 */
public class RxOperatorActivity extends AppCompatActivity {
    public static final String TAG = "RxOperatorActivity";
    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button4)
    Button mButton4;
    @BindView(R.id.button5)
    Button mButton5;
    @BindView(R.id.button6)
    Button mButton6;
    @BindView(R.id.button7)
    Button mButton7;
    @BindView(R.id.button8)
    Button mButton8;
    @BindView(R.id.button9)
    Button mButton9;
    @BindView(R.id.button10)
    Button mButton10;
    @BindView(R.id.button11)
    Button mButton11;
    @BindView(R.id.button12)
    Button mButton12;
    @BindView(R.id.button13)
    Button mButton13;
    @BindView(R.id.button14)
    Button mButton14;
    @BindView(R.id.button15)
    Button mButton15;
    @BindView(R.id.button16)
    Button mButton16;
    @BindView(R.id.button17)
    Button mButton17;
    @BindView(R.id.button18)
    Button mButton18;
    @BindView(R.id.button19)
    Button mButton19;
    @BindView(R.id.button20)
    Button mButton20;
    private Unbinder unbinder;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
        }
        setContentView(R.layout.activity_main3);
        unbinder = ButterKnife.bind(this);
        //        Disposable buffer = new ViewClickOnSubscribe(findViewById(R.id.button15))
        //                .subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .map(o -> 1)
        //                .buffer(2, TimeUnit.SECONDS)
        //                .subscribe(i -> Log.e(TAG, "onCreate: got " + i + "click"));
        //        mCompositeDisposable.add(buffer);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mCompositeDisposable.dispose();
    }

    /**
     * 一次发射事件，是阻塞事件??
     */
    @OnClick(R.id.button)
    public void onMButtonClicked() {
        Observable
                .create((ObservableOnSubscribe<String>) e -> {
                    e.onNext("1");
                    e.onNext("2");
                    e.onError(new Throwable("planning error"));
                    e.onComplete();
                })
                .compose(RxUtil.workIoObMain())
                .doFinally(() -> Log.d(TAG, "onMButtonClicked: doFinally"))
                .doAfterTerminate(() -> Log.d(TAG, "onMButtonClicked: doAfterTerminate"))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: it's correct");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onNext: it's correct", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: it's complete whatever correct or error");
                    }
                });

    }

    @OnClick(R.id.button2)
    public void onMButton2Clicked() {
        Disposable skipWhile = Observable
                .just("1", "2", "3")
                .skipWhile(s -> s.equals("1"))
                .compose(RxUtil.workIoObMain())
                .subscribe(s -> Log.e(TAG, "accept: " + s));
        mCompositeDisposable.add(skipWhile);

    }

    @OnClick(R.id.button3)
    public void onMButton3Clicked() {
        Single<String> single = Single
                .create(e -> {
                    e.onSuccess("sss");
                    e.onSuccess("as");
                });
        Disposable disposable = single
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.e(TAG, "accept: " + s));

        mCompositeDisposable.add(disposable);


    }

    @OnClick(R.id.button4)
    public void onMButton4Clicked() {
        Disposable startWith = Observable.just("2", "3")
                .startWith("1")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.e(TAG, "accept: " + s));
        mCompositeDisposable.add(startWith);

    }

    /**
     * merge合并多个流按事件发生时间交错，形成一个新的流，不管哪个流发出error立即停止流
     * mergeDelayError 合并多个流按事件发生时间交错，形成一个新的流，延迟error直到所有流走完
     */
    @OnClick(R.id.button5)
    public void onMButton5Clicked() {
        Observable<String> just = Observable.just("2", "3");
        Observable<String> just1 = Observable.just("4", "5");
        //        Observable<String> just2 = Observable.just("6", "7");
        Observable<String> just2 = new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                observer.onNext("7");
                try {
                    Thread.sleep(1000);
                    observer.onError(new Throwable("no such element"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Disposable merge = Observable.mergeDelayError(just, just2, just1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.e(TAG, "accept: " + s), throwable -> {
                    Log.e(TAG, "accept: throw a exception -->");
                    throwable.printStackTrace(System.out);
                });
        mCompositeDisposable.add(merge);
    }

    /**
     * 通过一个方法组合多个流的值生成一个新的值，并发射这个值
     */
    @OnClick(R.id.button6)
    public void onMButton6Clicked() {
        Observable<String> just = Observable.just("2", "3");
        Observable<String> just1 = Observable.just("4", "5");
        //        Observable<String> just2 = Observable.just("6", "7");
        Observable<String> just2 = Observable.create(e -> {
            e.onNext("6");
            Thread.sleep(1000);
            e.onError(new Throwable("no such element"));
        });
        // 发射源可以重用 如果没有定义异常观察者，将直接退出应用
        Disposable zip = Observable
                .zip(just, just1, just2, (s, s2, s3) -> s + s2 + s3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.e(TAG, "accept: s = " + s), throwable -> {
                    Log.e(TAG, "accept: print the error in case the app be stopped");
                    throwable.printStackTrace();
                });
        mCompositeDisposable.add(zip);
    }

    /**
     * 与上一个操作符相似
     */
    @OnClick(R.id.button7)
    public void onMButton7Clicked() {
        Observable<String> just1 = Observable.just("1");
        Observable<String> just2 = Observable.just("2");

        Disposable zipWith = just1.zipWith(just2, (s, s2) -> s + s2)
                .subscribe(s -> Log.e(TAG, "onMButton7Clicked: accept " + s));
        mCompositeDisposable.add(zipWith);
    }

    /**
     * combineLasted 第一个流的最后一个元素 分别与第二个流的事件匹配
     */
    @OnClick(R.id.button8)
    public void onMButton8Clicked() {
        Observable<String> just = Observable.create(e -> {
            System.out.println("just start time " + System.currentTimeMillis());
            e.onNext("1");
            Thread.sleep(2000);
            e.onNext("2");
        });
        Observable<String> just1 = Observable.create(e -> {
            System.out.println("just1 start time " + System.currentTimeMillis());
            Thread.sleep(1000);
            e.onNext("A");
            Thread.sleep(2000);
            e.onNext("B");
        });
        Disposable combineLatest = Observable.combineLatest(just, just1, (s, s1) -> s + s1)
                .subscribe(s -> Log.e(TAG, "onMButton8Clicked: accept " + s));
        mCompositeDisposable.add(combineLatest);
    }

    /**
     * 多个流，取最先发射数据的流，抛弃其他流
     */
    @OnClick(R.id.button9)
    public void onMButton9Clicked() {
        List<ObservableSource<String>> list = new ArrayList<>();

        ObservableSource<String> just1 = Observable.just("1", "3", "5");
        ObservableSource<String> just2 = Observable.just("2", "4", "6");
        list.add(just1);
        list.add(just2);
        Disposable amb = Observable.amb(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.e(TAG, "onMButton9Clicked: accept " + s));
        mCompositeDisposable.add(amb);
    }

    @OnClick(R.id.button10)
    public void onMButton10Clicked() {
        Disposable defaultIfEmpty = Observable.just("1")
                .defaultIfEmpty("3")
                .subscribe(s -> Log.e(TAG, "onMButton10Clicked: accept " + s));
        mCompositeDisposable.add(defaultIfEmpty);
    }

    /**
     * skipUntil 丢弃流一的数据直到流二发射数据，???? 原理未知
     */
    @OnClick(R.id.button11)
    public void onMButton11Clicked() {
        //        ObservableSource<String> just2 = Observable.just("1", "3", "5");
        Observable<Object> just2 = Observable.create(e -> {
            e.onNext("1");
            Thread.sleep(1000);
            e.onNext("3");
            e.onNext("5");
        });
        Disposable skipUntil = Observable.just("2", "4", "6")
                .skipUntil(just2)
                .subscribe(s -> Log.e(TAG, "onMButton10Clicked: accept " + s));
        mCompositeDisposable.add(skipUntil);
    }

    /**
     * skipWhile( return boolean) 丢弃发射的数据直到 boolean = false,并发射在此之后的所有数据
     */
    @OnClick(R.id.button12)
    public void onMButton12Clicked() {
        Disposable skipWhile = Observable.just("2", "4", "6")
                .skipWhile(s -> {
                    Log.e(TAG, "onMButton12Clicked: skip check ->" + s);
                    boolean a = (s.equals("2"));
                    Log.e(TAG, "onMButton12Clicked: check result ->" + a);
                    return a;
                })
                .subscribe(s -> Log.e(TAG, "onMButton10Clicked: accept " + s));
        mCompositeDisposable.add(skipWhile);
    }

    /**
     * repeatUntil 重复返回流循环事件，直到 repeatUntil 中 return 返回true
     */
    @OnClick(R.id.button13)
    public void onMButton13Clicked() {
        Disposable repeatUntil = Observable
                .just("1").repeatUntil(() -> {
                    System.out.println(System.currentTimeMillis());
                    Thread.sleep(1000);
                    System.out.println(System.currentTimeMillis());
                    return false;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.e(TAG, "onMButton13Clicked: accept " + s));
        mCompositeDisposable.add(repeatUntil);
    }

    /**
     * interval 间隔时间持续发送心跳
     * 倒计时60秒
     */
    @OnClick(R.id.button14)
    public void onMButton14Clicked() {
        int countDownSec = 60;
        Observable.interval(1, 1, TimeUnit.SECONDS)
                .take(60)
                .doOnSubscribe(disposable -> {
                    mCompositeDisposable.add(disposable);
                    mButton14.setEnabled(false);
                })
                .map(aLong -> {
                    int i = (int) (countDownSec - aLong - 1);
                    return String.valueOf(i);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(String s) {
                        mButton14.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("startCountDown", "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        mButton14.setText("获取验证码");
                        mButton14.setEnabled(true);
                    }
                });
    }

    @OnClick(R.id.button15)
    public void onMButton15Clicked() {
        Disposable groupBy = Observable
                .just(1, 2, 3, 4, 5, 6, 7)
                .groupBy(s -> {
                    if (s < 4)
                        return 0;
                    return 1;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .forEach(gby -> {
                    int b = gby.getKey();
                    switch (b) {
                        case 0:
                            gby.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(integer -> Log.e(TAG, "accept: group0 value-> " + integer));
                            break;
                        case 1:
                            gby.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(integer -> Log.e(TAG, "accept: group1 value-> " + integer));
                            break;
                    }
                });

        mCompositeDisposable.add(groupBy);

    }

    @OnClick(R.id.button16)
    public void onMButton16Clicked() {
        Disposable scan = Observable
                .just(1, 2, 3, 4, 5, 6, 7)
                .scan((integer, integer2) -> integer + integer2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Log.e(TAG, "onMButton16Clicked: accept " + integer));
        mCompositeDisposable.add(scan);
    }

    @OnClick(R.id.button17)
    public void onMButton17Clicked() {
        Observable
                .create(e -> {
                    Log.e(TAG, "onMButton17Clicked: looper -> " +
                            Thread.currentThread().getName());
                    e.onNext("1");
                    e.onNext("2");
                })
                .compose(RxUtil.workIoObMain())
                .subscribe(s -> {
                    Log.e(TAG, "onMButton17Clicked: looper ->" +
                            Thread.currentThread().getName());
                    Log.e(TAG, "onMButton17Clicked: accept " + s);
                });

    }

    /**
     * 函数转为发射源
     */
    @OnClick(R.id.button18)
    public void onMButton18Clicked() {
        Observable.just(fooAction(10))
                .compose(RxUtil.workIoObMain())
                .subscribe(integer -> UIUtil.showToast(
                        RxOperatorActivity.this, "received sum of fooAction"));
    }

    /**
     * 无意义的类
     *
     * @param i no sense num
     * @return
     */
    private int fooAction(int i) {
        int sum = 0;
        for (int j = 0; j < i; j++) {
            for (int k = j + 1; k < i; k++) {
                int tmp = j + k;
                sum += tmp;
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sum;
    }

    @OnClick(R.id.button19)
    public void onMButton19Clicked() {
        Observable.create((ObservableOnSubscribe<String>) e -> {
            e.onNext("1");
            e.onNext("2");
//            e.onError(new Throwable("planning error"));
            e.onNext("1");
            e.onNext("2");
        })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> "1111")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    @OnClick(R.id.button20)
    public void onMButton20Clicked() {
        Observable.create((ObservableOnSubscribe<String>) e -> {
            e.onNext("1");
            e.onNext("2");
            e.onError(new Throwable("planning error"));
            e.onNext("1");
            e.onNext("2");
        })
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(throwable -> {
                    return Observable.error(new Throwable(throwable.getMessage()));
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.d(TAG, "onNext: " + s),
                        throwable -> Log.e(TAG, "onError: " + throwable));
    }
}
