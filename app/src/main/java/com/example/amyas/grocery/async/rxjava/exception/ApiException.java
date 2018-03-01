package com.example.amyas.grocery.async.rxjava.exception;

/**
 * author: Amyas
 * date: 2018/3/1
 */

public class ApiException extends Exception{
    public int code;
    public String message;

    public ApiException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
