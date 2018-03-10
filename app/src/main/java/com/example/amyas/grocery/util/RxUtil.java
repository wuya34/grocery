package com.example.amyas.grocery.util;

import android.util.Log;

import com.example.amyas.grocery.Constant;
import com.example.amyas.grocery.async.rxjava.exception.DispatchException;
import com.example.amyas.grocery.bean.BaseBean;

import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Amyas
 * date: 2018/1/12
 */


public class RxUtil {
    public static final String TAG = "RxUtil";

    /**
     * 事件源转换，io中执行任务，main中执行回调
     *
     * @param <T> Observable 类以及其导出类
     * @return 已定义事件线的事件源
     */
    public static <T> ObservableTransformer<T, T> workIoObMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 事件源转换，io中执行任务以及回调
     *
     * @param <T> Observable 类以及其导出类
     * @return 已定义事件线的事件源
     */
    public static <T> ObservableTransformer<T, T> workIoObIo() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    /**
     * 显示ProgressBar
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> showProgressBar() {
        return upstream -> upstream
                .doOnSubscribe(disposable -> Log.d(TAG, "accept: start"))
                .doFinally((Action) () -> {
                    Log.d(TAG, "run: stop");
                });

    }


    /**
     * 事件源转换，io中执行任务，main中执行回调
     *
     * @param <T> Observable 类以及其导出类
     * @return 已定义事件线的事件源
     */
    public static <T> ObservableTransformer<BaseBean<T>, BaseBean<T>> workIoObMainForBaseBean() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 所有异常检查
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseBean<T>, BaseBean<T>> responseCheckForBaseBean() {
        return upstream -> upstream.map(getHttpCodeCheckFunc())
                .onErrorResumeNext(getNetWorkCheckFunc());
    }

    /**
     * 显示ProgressBar
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseBean<T>, BaseBean<T>> showProgressBarForBaseBean() {
        return upstream -> upstream.doOnSubscribe(baseBean -> Log.d(TAG, "start"))
                .doFinally(() -> Log.d(TAG, "stop"));

    }

    /**
     * BaseBean compose 组合
     * 检查异常以及显示ProgressBar
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseBean<T>, BaseBean<T>> defaultComposeForBaseBean() {
        return upstream -> upstream.compose(workIoObMainForBaseBean())
                .compose(showProgressBarForBaseBean())
                .compose(responseCheckForBaseBean());
    }


    /**
     * http以及服务器 异常检查
     * 对应code抛出相应的错误提示，在页面toast中提示
     *
     * @param <T>
     * @return
     */
    public static <T> Function<BaseBean<T>, BaseBean<T>> getHttpCodeCheckFunc() {
        return tBaseBean -> {
            Log.e(TAG, tBaseBean.toString());
           if (tBaseBean.getCode() != Constant.REQUEST_OK) {
                String msg = DispatchException.dispatch(tBaseBean.getCode());
                throw new RuntimeException(msg);
            }
            return tBaseBean;
        };
    }

    /**
     * 网络可用检查
     *
     * @param <T>
     * @return
     */
    public static <T> Function<Throwable, ObservableSource<? extends BaseBean<T>>> getNetWorkCheckFunc() {
        return throwable -> (ObservableSource<BaseBean<T>>) observer ->
                observer.onError(new Throwable("getNetWorkCheckFunc: mock no net work error"));
    }
}
