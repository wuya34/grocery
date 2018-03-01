package com.example.amyas.grocery.gallery;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.amyas.grocery.R;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class Main12Activity extends AppCompatActivity {
    public static final String TAG = "Main12Activity";

    private static final String[] bitmapUrlList = PhotoUrlResource.resource;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private Unbinder unbinder;
    private LruCache<String, Bitmap> mLruCache;
//    private ThumbnailDownloader<MyAdapter.MyHolder> mThumbnailDownloader;
    private DiskLruCache mDiskLruCache;
    private int diskLruCacheSize = 1024 * 1024 * 50;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * 获取磁盘缓存目录
     *
     * @param context    上下文
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

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i ++){
            String hex = Integer.toHexString(0xFF & bytes[i]);//得到十六进制字符串
            if (hex.length() == 1){
                sb.append('0');
            }
            sb.append(hex);
        }
        return  sb.toString();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        unbinder = ButterKnife.bind(this);

        configCache();
        configDiskCache();
        MyAdapter adapter = new MyAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(adapter);
    }

    private void configDiskCache() {
        try {
            File file = getDiskCacheDir(this, "bitmap");
            if (!file.exists()){
                file.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(
                    file, getAppVersion(this),1,diskLruCacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void configCache() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024 );
        int cacheSize = maxMemory / 8;
        // cacheSize 单位是KB
        Log.e(TAG, "MyAdapter: cacheSize = " + cacheSize);
        mLruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mCompositeDisposable.dispose();
//        mThumbnailDownloader.clearQueue();
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


    public boolean saveInputStreamToDiskCache(InputStream inputStream, OutputStream outputStream) {
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(inputStream, 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
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
        Log.e(TAG, "hashKeyForDisk: code = "+code );
        return code;
    }

    private Observable<Bitmap> getBitmapFromMemoryCache(final String url) {

        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                Bitmap bitmap = mLruCache.get(url);
                if (bitmap != null) {
                    Log.e(TAG, "subscribe: 内存缓存读取数据");
                    e.onNext(bitmap);
                } else {
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    private Observable<Bitmap> getBitmapFromDiskCache(final String url) {

        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                String key = hashKeyForDisk(url);
                Bitmap bitmap = null;
                DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
                if (snapShot != null) {
                    InputStream is = snapShot.getInputStream(0);
                    bitmap = BitmapFactory.decodeStream(is);
                }
                if (bitmap != null) {
                    // 放到内存缓存中
                    Log.e(TAG, "subscribe: 存入内存缓存");
                    mLruCache.put(url, bitmap);
                    e.onNext(bitmap);
                    Log.e(TAG, "subscribe: 磁盘缓存读取数据");
                } else {
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    private Observable<Bitmap> getBitmapFromNet(final String url) {
        return Observable.create((ObservableOnSubscribe<Bitmap>) e -> {
            InputStream stream = FetchPhoto.getInputStreamByUrl(url);
            Log.e(TAG, "subscribe: stream -> "+ stream );
            if (stream != null) {
                // 放到内存缓存中
                Bitmap bitmap = FetchPhoto.decodeBitmapFromStream(stream);
                if (bitmap!=null){
                    mLruCache.put(url, bitmap);
                    Log.e(TAG, "subscribe: 存入内存缓存");
                }
                // 放到磁盘缓存中
                try {
                    String key = hashKeyForDisk(url);
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (saveInputStreamToDiskCache(stream, outputStream)) {
                            editor.commit();
                            Log.e(TAG, "subscribe: 存入磁盘缓存");
                        } else {
                            editor.abort();
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Log.e(TAG, "subscribe: 网络请求读取数据");


                e.onNext(bitmap);
            } else {
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
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
            holder.mImageView.setImageResource(R.drawable.oval);
            // rxjava 后台获取图片
            holder.getBitmap(url);
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

            private void getBitmap(String url) {
                Log.e(TAG, "getBitmap: on holder");
                Disposable disposable = Observable.concat(
                        getBitmapFromMemoryCache(url),
                        getBitmapFromDiskCache(url),
                        getBitmapFromNet(url))
                        .take(1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(bitmap -> mImageView.setImageBitmap(bitmap));
                mCompositeDisposable.add(disposable);

            }
        }
    }


}
