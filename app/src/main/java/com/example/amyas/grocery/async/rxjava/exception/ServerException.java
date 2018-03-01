package com.example.amyas.grocery.async.rxjava.exception;

/**
 * author: Amyas
 * date: 2018/3/1
 */

public class ServerException extends Exception {
    private int code;
    private String msg;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    @Override

    public String toString() {
        return "ServerException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
