package com.example.amyas.grocery.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * author: Amyas
 * date: 2018/3/10
 */

public class NetWorkUtil {
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager==null){
            return false;
        }

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo==null || !networkInfo.isAvailable()){
            return false;
        }
        return true;
    }
}
