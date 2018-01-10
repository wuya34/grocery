package com.example.amyas.customwidget.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.amyas.customwidget.R;
import com.example.amyas.customwidget.gallery.FetchPhoto;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Rxjava api 使用以及自定义viewgroup控件 DragViewGroup
 */
public class Main3Activity extends AppCompatActivity {
    public static final String TAG = "Main3Activity";
    @BindView(R.id.camila)
    ImageView mCamila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

//        Observable.create(new ObservableOnSubscribe<Bitmap>() {
//            @Override
//            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
//                Log.e(TAG, "accept: subscribe Thread.currentThread().getName() ="
//                        + Thread.currentThread().getName());
//                Bitmap bitmap = FetchPhoto.getInputStreamByUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=95608140,3153770266&fm=27&gp=0.jpg");
//                e.onNext(bitmap);
//            }
//        }).observeOn(Schedulers.io())
//                .doOnNext(new Consumer<Bitmap>() {
//                    @Override
//                    public void accept(Bitmap bitmap) throws Exception {
//                        Log.e(TAG, "accept: doOnNext bitmap = " + bitmap);
//                        Log.e(TAG, "accept: doOnNext Thread.currentThread().getName() ="
//                                + Thread.currentThread().getName());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Bitmap>() {
//                    @Override
//                    public void accept(Bitmap bitmap) throws Exception {
//                        mCamila.setImageBitmap(bitmap);
//                        Log.e(TAG, "accept: subscribe Thread.currentThread().getName() ="
//                                + Thread.currentThread().getName());
//                    }
//                });

        //        Observable.just(1)
        //                .observeOn(Schedulers.io())
        //                .doOnNext(new Consumer<Integer>() {
        //                    @Override
        //                    public void accept(Integer integer) throws Exception {
        //                        Log.e(TAG, "accept: doOnNext Thread.currentThread().getName() ="
        //                                + Thread.currentThread().getName());
        //                    }
        //                })
        //                .map(new Function<Integer, String>() {
        //                    @Override
        //                    public String apply(Integer integer) throws Exception {
        //                        Log.e(TAG, "accept: apply Thread.currentThread().getName() ="
        //                                + Thread.currentThread().getName());
        //                        return String.valueOf(integer);
        //                    }
        //                })
        //                .subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Consumer<String>() {
        //                    @Override
        //                    public void accept(String s) throws Exception {
        //                        Log.e(TAG, "accept: subscribe Thread.currentThread().getName() ="
        //                                + Thread.currentThread().getName());
        //                    }
        //                });

    }
}
