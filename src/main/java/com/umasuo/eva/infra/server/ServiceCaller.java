package com.umasuo.eva.infra.server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by umasuo on 17/7/7.
 * 整个APP共用同一个caller, 后面视网络情况看是否需要为每个service提供一个.
 */
public abstract class ServiceCaller {

    protected static String API_URL = "http://192.168.1.19:8809";

    protected static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ServiceCaller() {
    }
}
