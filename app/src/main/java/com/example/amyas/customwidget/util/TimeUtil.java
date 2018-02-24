package com.example.amyas.customwidget.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * author: Amyas
 * date: 2018/1/23
 */

public class TimeUtil {
    /**
     * 计算两个时间差值，大于一天则返回`x天前`，小于则返回`x小时前`，不足一小时返回`x小时前`
     * 不足一分钟返回`刚刚`
     * example ->
     *
     * @param startTime 第一个时间
     * @param nowDate   long 型的时间，System.currentTimeMillis()获取
     * @return
     */
    public static String getPastTimeFlexible(Date startTime, long nowDate) {
        long nd = 86400000;
        long nh = 3600000;
        long nm = 60000;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long start = startTime.getTime();
        long diff = nowDate - start;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        if (day > 0) {
            return day + "天前";
        }
        if (hour > 0) {
            return hour + "小时前";
        }
        if (min > 0) {
            return min + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 日期转时间, 格式为 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String date2StringSecond(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    /**
     * 日期转时间 格式为 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String date2StringDay(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 日期转时间 格式为 yyyy-MM-dd HH:mm
     * @param date
     * @return
     */
    public static String date2StringMinute(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
        return formatter.format(date);
    }


    public static String getSysYear() {
        Calendar date = Calendar.getInstance();
        return String.valueOf(date.get(Calendar.YEAR));
    }

    /**
     * 计算两个日期之间是否相差超过一天
     * @param past 之前的时间
     * @param now 现在的时间
     * @return
     */
    public static boolean isPastADay(Date past, Date now){
        long nd = 86400000;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long start = past.getTime();
        long end = now.getTime();
        if (end<start){
            throw new IllegalArgumentException("参数2 比 参数1 小");
        }
        long diff = end-start;
        long day = diff/nd;
        if (day>=1){
            return true;
        }
        return false;
    }
}
