package com.example.amyas.grocery.retrofit;

import com.example.amyas.grocery.async.rxjava.BaseResponse;
import com.example.amyas.grocery.async.rxjava.LandResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * author: Amyas
 * date: 2018/1/11
 */

public interface Api {
    String token = "";

    @GET("customer/land/list")
    Observable<BaseResponse<LandResponse>> getLandList(@Header("Token") String token,
                                                       @Query("name") String name,
                                                       @Query("page") int page,
                                                       @Query("limit") int limit);
}
