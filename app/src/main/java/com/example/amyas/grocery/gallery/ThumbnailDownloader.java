package com.example.amyas.grocery.gallery;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * author: Amyas
 * date: 2018/1/4
 */

public class ThumbnailDownloader<T> extends HandlerThread {
    public static final String TAG = "ThumbnailDownloader";
    public static final int MESSAGE_WHAT = 0;
    private Handler responseHandler;
    private Handler requestHandler;
    private onThumbnailDownloadListener<T> mListener;
    private boolean hasQuit = false;

    public ThumbnailDownloader(Handler handler) {
        super(TAG);
        responseHandler = handler;
    }

    public void setListener(onThumbnailDownloadListener<T> listener) {
        mListener = listener;
    }

    @Override
    public boolean quit() {
        hasQuit = true;
        return super.quit();

    }

    public void clearQueue() {
        requestHandler.removeMessages(MESSAGE_WHAT);

    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        requestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_WHAT) {
                    Tuple2 m = ((Tuple2) msg.obj);
                    handleRequest(m.getViewHolder(), m.getUrl());
                }
            }
        };
    }

    public void queueThumbnail(T o, String url) {
        requestHandler.obtainMessage(MESSAGE_WHAT, new Tuple2(url, o)).sendToTarget();
    }

    /**
     * 处理下载请求以及返回主线程更新ui
     *
     * @param target
     */
    private void handleRequest(final T target, final String url) {
        //        Log.e(TAG, "handleRequest: target ("+target+")" );
        //        Log.e(TAG, "handleRequest: mConcurrentMap.contain(target)= "+mConcurrentMap.containsKey(target) );
        if (url == null) {
            //            Log.e(TAG, "handleRequest: url is null");
            return;
        }
//        final Bitmap bitmap = FetchPhoto.getInputStreamByUrl(url);
        responseHandler.post(new Runnable() {
            @Override
            public void run() {
                //                Log.e(TAG, "run: check url is ("+url+")" );
                if (hasQuit) {
                    Log.e(TAG, "run: ThumbnailDownloader hasQuit = true");
                    return;
                }
//                mListener.onThumbnailDownloaded(target, bitmap, url);
            }
        });
    }

    interface onThumbnailDownloadListener<T> {
        void onThumbnailDownloaded(T target, Bitmap bitmap, String url);
    }

    class Tuple2 {
        private String url;
        private T viewHolder;

        public Tuple2(String url, T viewHolder) {
            this.url = url;
            this.viewHolder = viewHolder;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public T getViewHolder() {
            return viewHolder;
        }

        public void setViewHolder(T viewHolder) {
            this.viewHolder = viewHolder;
        }
    }

}
