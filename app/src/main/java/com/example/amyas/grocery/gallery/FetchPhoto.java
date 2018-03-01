package com.example.amyas.grocery.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * author: Amyas
 * date: 2018/1/4
 */

public class FetchPhoto {
    public static final String TAG = "FetchPhoto";

    public static InputStream getInputStreamByUrl(String url) {
////        Bitmap bitmap = null;
//        HttpURLConnection con = null;
//        try {
//            URL bitmapUrl = new URL(url);
//            con = (HttpURLConnection) bitmapUrl.openConnection();
//            con.setConnectTimeout(5000);
//            con.setReadTimeout(10000);
//            InputStream stream = con.getInputStream();
//            return stream;
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.e("FetchPhoto", "getInputStreamByUrl:  failed");
//        } finally {
//            if (con != null) {
//                con.disconnect();
//            }
//        }
//        return null;
        try {
            InputStream input = new URL(url).openStream();
            assert input!=null;
            return input;
        } catch (IOException e) {
            e.printStackTrace();
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
            return decodeBitmapFromByteArray(outputStream.toByteArray());
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

    public static Bitmap decodeBitmapFromByteArray(byte[] array){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(array, 0, array.length, options);
        options.inSampleSize = calculateSimpleSize(options);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length, options);
//        Log.e(TAG, "getInputStreamByUrl: bitmap = "+ bitmap.getByteCount());
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
