package com.example.amyas.grocery.async.rxjava.exception;

/**
 * author: Amyas
 * date: 2018/3/1
 */

/**
 * 约定异常
 */

public class Error {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1003;
}
