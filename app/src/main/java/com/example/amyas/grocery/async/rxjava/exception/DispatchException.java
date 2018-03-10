package com.example.amyas.grocery.async.rxjava.exception;


/**
 * author: Amyas
 * date: 2018/3/10
 * 请求错误码分发器
 */

public class DispatchException {

    public DispatchException() {}

    public static String dispatch(int code){
        String msg;
        switch (code){
            case Error.TOKEN_ERROR:
                msg = "会话超时，请点击 我的-> 设置 重新登录";
                break;
            default:
                msg = "没有网络连接";
                break;
        }
        return msg;
    }
}
