package com.example.amyas.customwidget.util;

import java.util.regex.Pattern;

import javax.annotation.Nullable;

/**
 * author: Amyas
 * date: 2018/1/17
 */

public class StringUtils {

    public static final String REGEX_MOBILE = "^[1][3578][0-9]{9}$";

    public static <T> T checkNotNull(T reference) {
        if(reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static boolean isNullOrEmpty(@Nullable String string) {
        return string == null || string.length() == 0;
    }

    /**
     * 校验手机号
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
}
