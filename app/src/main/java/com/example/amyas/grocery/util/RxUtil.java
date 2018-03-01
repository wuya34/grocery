package com.example.amyas.grocery.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Amyas
 * date: 2018/1/12
 */


public class RxUtil {
    /**
     * 事件源转换，io中执行任务，main中执行回调
     * @param <T> Observable 类以及其导出类
     * @return 已定义事件线的事件源
     */
    public static <T> ObservableTransformer<T, T> workIoObMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 事件源转换，io中执行任务，main中执行回调
     * @param <T> Observable 类以及其导出类
     * @return 已定义事件线的事件源
     */
    public static <T> ObservableTransformer<T, T> workIoObIo() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}
