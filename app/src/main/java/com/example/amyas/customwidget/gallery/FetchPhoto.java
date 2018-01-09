package com.example.amyas.customwidget.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author: Amyas
 * date: 2018/1/4
 */

public class FetchPhoto {
    public static final String TAG = "FetchPhoto";

    public static Bitmap getBitmapByUrl(String url) {
//        Bitmap bitmap = null;
        HttpURLConnection con = null;
        try {
            URL bitmapUrl = new URL(url);
            con = (HttpURLConnection) bitmapUrl.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(10000);
            InputStream stream = con.getInputStream();
            return decodeBitmapFromStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("FetchPhoto", "getBitmapByUrl:  failed");
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

    public static Bitmap decodeBitmapFromStream(InputStream in){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            int n;
            byte[] buffer = new byte[1024];
            while ((n = in.read(buffer)) > 0) {
                outputStream.write(buffer, 0, n);
            }
            return decodeSampledBitmapFromByteArray(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Bitmap decodeSampledBitmapFromByteArray(byte[] array){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(array, 0, array.length, options);
        options.inSampleSize = calculateSimpleSize(options);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length, options);
//        Log.e(TAG, "getBitmapByUrl: bitmap = "+ bitmap.getByteCount());
        return bitmap;
    }

    public static int calculateSimpleSize(BitmapFactory.Options options) {
        return calculateSimpleSize(options, 175, 175);
    }

    public static int calculateSimpleSize(BitmapFactory.Options options,int defaultHeight, int defaultWidth) {
        int height = options.outHeight;
        int width = options.outWidth;
        int simpleSize = 1;
        if (height > defaultHeight && width > defaultWidth) {
            int heightRatio = height / defaultHeight;
            int widthRatio = width / defaultWidth;
            simpleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return simpleSize;
    }
}
