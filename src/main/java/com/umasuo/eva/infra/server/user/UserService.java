package com.umasuo.eva.infra.server.user;

import com.umasuo.eva.domain.user.dto.QuickSignIn;
import com.umasuo.eva.domain.user.dto.SignInResult;
import com.umasuo.eva.domain.user.dto.UserView;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.infra.server.ServiceCaller;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by umasuo on 17/7/7.
 * 用户相关的API请求.
 */
public class UserService extends ServiceCaller {

    private Service service;

    public UserService() {
        service = retrofit.create(Service.class);
    }


    /**
     * 获取登录/注册手机验证码
     */
    public void getSmsCode(String phone) {

        Call<Void> caller = service.getSmsCode(phone);

        //异步发起请求，所有的网络请求都需要异步发起请求
        caller.enqueue(
                new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        //请求成功
                        LogControl.debug("UserService", "Success");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        //请求失败
                        LogControl.debug("UserService", "Success");
                        // TODO: 17/7/8 显示错误信息
                    }
                }
        );
    }

    /**
     * 短信登录到云端
     *
     * @param phone       手机号
     * @param developerId 开发者ID
     * @param smsCode     收到的sms code
     */
    public void signIn(String phone, String developerId, String smsCode) {

        //todo 检查各个参数的值
        QuickSignIn quickSignIn = new QuickSignIn(phone, developerId, smsCode);

        Call<SignInResult> caller = service.quickSignIn(quickSignIn);

        //异步发起请求，所有的网络请求都需要异步发起请求
        caller.enqueue(
                new Callback<SignInResult>() {
                    @Override
                    public void onResponse(Call<SignInResult> call, Response<SignInResult> response) {
                        //请求成功
                        LogControl.debug("UserService", " signin Success");
                        SignInResult result = response.body();
                    }

                    @Override
                    public void onFailure(Call<SignInResult> call, Throwable t) {
                        //请求失败
                        LogControl.debug("UserService", "Success");
                        // TODO: 17/7/8 显示错误信息
                    }
                }
        );
    }

    /**
     * 更新用户信息
     */
    public void updateInfo() {

    }

    /**
     * 用于构建http 请求的base.
     */
    private interface Service {

        @GET("/v1/users/{id}")
        Call<UserView> getUserInfo(@Path("id") String id);

        @POST("/v1/users/validationCodes")
        Call<Void> getSmsCode(@Query("phoneNumber") String phone);

        @POST("/v1/users/signin")
        Call<SignInResult> quickSignIn(@Body QuickSignIn quickSignIn);
    }
}