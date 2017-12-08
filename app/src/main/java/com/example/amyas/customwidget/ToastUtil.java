package com.example.amyas.customwidget;

import android.content.Context;
import android.widget.Toast;

/**
 * author: Amyas
 * date: 2017/11/15
 */

public class ToastUtil {
    private static Toast toast;
    public static void showToast(Context context, String content){
        if (toast==null){
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);

        }else {
            toast.setText(content);
        }
        toast.show();

    }
}
