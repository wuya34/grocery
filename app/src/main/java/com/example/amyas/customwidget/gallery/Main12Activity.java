package com.example.amyas.customwidget.gallery;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.amyas.customwidget.R;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;


public class Main12Activity extends AppCompatActivity {
    public static final String TAG = "Main12Activity";

    private static final String[] bitmapUrlList = PhotoUrlResource.resource;
    private final Object diskLruLock = new Object();
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private Unbinder unbinder;
    private LruCache<String, Bitmap> mLruCache;
    private ThumbnailDownloader<MyAdapter.MyHolder> mThumbnailDownloader;
    private DiskLruCache mDiskLruCache;
    private String diskLruBitmapFileName = "bitmap";
    private int diskLruCacheSize = 1024 * 1024 * 10;
    private boolean diskLruIsStarting = true;

    /**
     * 获取磁盘缓存目录
     *
     * @param context 上下文
     * @param uniqueName 目录分类 例如："bitmap"
     * @return 目录file文件
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        unbinder = ButterKnife.bind(this);


        configCache();
        Handler responseHandler = new Handler();
        mThumbnailDownloader = new ThumbnailDownloader<>(responseHandler);
        mThumbnailDownloader.setListener(new ThumbnailDownloader.onThumbnailDownloadListener<MyAdapter.MyHolder>() {

            @Override
            public void onThumbnailDownloaded(MyAdapter.MyHolder target, Bitmap bitmap, String url) {
                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                target.mImageView.setImageDrawable(drawable);
                //                Log.e(TAG, "onThumbnailDownloaded: 后台已下载图片并加入缓存");
                mLruCache.put(url, bitmap);
            }
        });
        MyAdapter adapter = new MyAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(adapter);
        mThumbnailDownloader.start();
        mThumbnailDownloader.getLooper();

        // 配置磁盘缓存
        File file = getDiskCacheDir(this, diskLruBitmapFileName);
        new initDiskLruCache().execute(file);
    }

    private void configCache() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024 / 1024 / 2);
        int cacheSize = maxMemory / 8;
        Log.e(TAG, "MyAdapter: cacheSize = " + cacheSize);
        mLruCache = new LruCache<>(cacheSize);
    }

    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mThumbnailDownloader.clearQueue();
    }

    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 根据url从磁盘缓存获取数据，如果磁盘没有，则开线程从网上获取
     *
     * @param url 图片的url
     * @return url对应的bitmap
     */
    public Bitmap getBitmapFromDiskCache(final String url) {
        synchronized (diskLruLock){
            while (diskLruIsStarting){
                try {
                    diskLruLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String key = hashKeyForDisk(url);
                        DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
                        if (snapshot!=null){
                            InputStream is = snapshot.getInputStream(0);
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                        }else {
                            // 网络请求获取图片

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        return null;
    }

    public void saveBitmapToDiskCache(String url, Bitmap bitmap){

    }

    public String hashKeyForDisk(String url) {
        String code;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            code = bytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            code = String.valueOf(url.hashCode());
        }
        return code;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        public MyAdapter() {
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_recyclerview, parent, false
            );
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            // TODO: 2018/1/4 根据position预加载图片
            String url = bitmapUrlList[position];
            Bitmap bitmap = mLruCache.get(url);
            if (bitmap != null) {
                //                Drawable drawable = new BitmapDrawable(getResources(),bitmap);
                holder.mImageView.setImageBitmap(bitmap);
                Log.e(TAG, "onBindViewHolder: 已从缓存加载图片");
            } else {
                holder.mImageView.setImageResource(R.drawable.oval);
                mThumbnailDownloader.queueThumbnail(holder, url);
                //                Log.e(TAG, "onBindViewHolder: download pair("+holder+", "+url+")" );
            }
        }



        @Override
        public int getItemCount() {
            return bitmapUrlList.length;
        }

        class MyHolder extends RecyclerView.ViewHolder {
            private ImageView mImageView;

            public MyHolder(View itemView) {
                super(itemView);
                mImageView = itemView.findViewById(R.id.image);
            }

            private void getBitmap(){

            }
        }
    }

    /**
     * 磁盘缓存初始化
     */
    private class initDiskLruCache extends AsyncTask<File, Void, Void> {

        @Override
        protected Void doInBackground(File... files) {
            synchronized (diskLruLock) {
                File dir = files[0];
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    mDiskLruCache = DiskLruCache.open(dir, getAppVersion(Main12Activity.this),
                            1, diskLruCacheSize);
                    diskLruIsStarting = false;
                    mDiskLruCache.notifyAll();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
