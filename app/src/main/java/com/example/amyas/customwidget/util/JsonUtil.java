package com.example.amyas.customwidget.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * author: Amyas
 * date: 2018/1/6
 */

public class JsonUtil {

    /**
     *  string 转 class
     * @param s
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T toBean(String s, Class<T> tClass){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(s, tClass);
    }

    /**
     *  JsonObject 转 class
     * @param s
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T toBean(JsonObject s, Class<T> tClass){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(s, tClass);
    }

    /**
     * String 转 Date
     * @param d
     * @return
     */
    public static Date str2Date(String d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            return sdf.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String 转 date(Long)
     * @param d
     * @return
     */
    public static long str2DateLong(String d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            return sdf.parse(d).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

}
