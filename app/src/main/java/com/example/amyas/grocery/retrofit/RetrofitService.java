package com.example.amyas.grocery.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: Amyas
 * date: 2018/1/11
 *  获取请求使用 mApi.method, {return # Observable}
 *  @return Observable
 *  请求url在Api.class中配置
 */

public class RetrofitService {
    public static final String BASE_PATH = "";
    public Api mApi;

    public static RetrofitService getInstance() {
        return singleInstance.instance;
    }

    private static class singleInstance {
        static final RetrofitService instance = new RetrofitService();
    }

    private RetrofitService() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
            //打印retrofit日志
//            LogUtil.d("retrofitBack = "+message);
            Log.e("retrofitBack", "RetrofitService: "+ message);
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        // 设置 http timeout
//        OkHttpClient client = new OkHttpClient();
//        client.newBuilder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .addInterceptor(loggingInterceptor);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Connection","close")
                            .build();
                    return chain.proceed(request);
                })
                .build();

        mApi = new Retrofit.Builder()
                .baseUrl(BASE_PATH)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api.class);
    }


}