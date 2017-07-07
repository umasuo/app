package com.umasuo.eva.tools.server;

import com.umasuo.eva.personal.data.UserModel;
import com.umasuo.eva.tools.log.LogControl;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by umasuo on 17/7/7.
 */

public class UserService extends ServiceCaller {

    private Service service;

    public UserService() {

//        String API_URL = "http://192.168.1.19:8809";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        service = retrofit.create(Service.class);
    }


    /**
     * 获取登录手机验证码
     */
    public void getSmsCode(String phone) {

        Call<Void> caller = service.getSmsCode(phone);

        try {
            Response<Void> response = caller.execute();
            if (response.code() == HttpURLConnection.HTTP_OK) {
                LogControl.debug("UserService", "get code succesed");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 17/7/7 处理获取用户信息失败的情况
            // 保持数据库中的不变
        }
    }

    /**
     * 根据userID 获取用户基本信息
     *
     * @param id String 用户ID
     * @return
     */
    public UserModel getUserInfo(String id) {

        Call<UserModel> caller = service.getUserInfo("");

        UserModel result = null;
        try {
            Response<UserModel> response = caller.execute();
            if (response.code() == HttpURLConnection.HTTP_OK) {
                result = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 17/7/7 处理获取用户信息失败的情况
            // 保持数据库中的不变
        }

        return result;
    }


    /**
     * 用于构建http 请求的base.
     */
    private interface Service {

        @GET("/v1/users/{id}")
        Call<UserModel> getUserInfo(@Path("id") String id);

        @POST("/v1/users/validationCodes")
        Call<Void> getSmsCode(@Query("phoneNumber") String phone);
    }

}