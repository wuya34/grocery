package com.example.amyas.grocery.util;

import com.example.amyas.grocery.async.rxjava.BaseResponse;
import com.example.amyas.grocery.async.rxjava.ExceptionEngine;
import com.example.amyas.grocery.async.rxjava.exception.ServerException;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
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



    public static class ServerResponseFunc<T> implements Function<BaseResponse<T>, List<T>>{

        @Override
        public List<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
            int code = tBaseResponse.getCode();
            if (code!=0){
                switch (code){
                    case 1001:
                        // 会话超时
                        throw new ServerException(tBaseResponse.getCode(),
                                tBaseResponse.getMsg());
                    default:
                        throw new RuntimeException(tBaseResponse.getMsg());
                }
            }
            return tBaseResponse.getData();

        }
    }

    public static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>>{

        @Override
        public Observable<T> apply(Throwable throwable) throws Exception {
            return Observable.error(ExceptionEngine.handleException(throwable));
        }
    }
}
