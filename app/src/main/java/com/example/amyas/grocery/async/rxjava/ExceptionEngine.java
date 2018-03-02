package com.example.amyas.grocery.async.rxjava;

import com.example.amyas.grocery.async.rxjava.exception.ApiException;
import com.example.amyas.grocery.async.rxjava.exception.Error;
import com.example.amyas.grocery.async.rxjava.exception.ServerException;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * author: Amyas
 * date: 2018/3/1
 */

public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e){
        ApiException ex;
        if (e instanceof HttpException){             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, Error.HTTP_ERROR);
            switch(httpException.code()){
                case UNAUTHORIZED:
                    ex.message = "未授权";
                    break;
                case FORBIDDEN:
                    ex.message = "禁止访问";
                    break;
                case NOT_FOUND:
                    ex.message = "没有找到网页";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message = "网关超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "内部错误";
                    break;
                case BAD_GATEWAY:
                    ex.message = "网络错误";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "服务不可用";
                    break;
                default:
                    ex.message = "网络错误";  //均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof ServerException){    //服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.getCode());
            ex.message = resultException.getMessage();
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ex = new ApiException(e, Error.PARSE_ERROR);
            ex.message = "解析错误";            //均视为解析错误
            return ex;
        }else if(e instanceof ConnectException){
            ex = new ApiException(e, Error.NETWORD_ERROR);
            ex.message = "连接失败";  //均视为网络错误
            return ex;
        }else {
            ex = new ApiException(e, Error.UNKNOWN);
            ex.message = "未知错误";          //未知错误
            return ex;
        }
    }
}
