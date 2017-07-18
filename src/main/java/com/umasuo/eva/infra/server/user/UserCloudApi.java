package com.umasuo.eva.infra.server.user;

import com.umasuo.eva.domain.user.dto.QuickSignIn;
import com.umasuo.eva.domain.user.dto.SignInResult;
import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.infra.server.ServiceCaller;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by umasuo on 17/7/7.
 * 用户相关的API请求.
 * todo 考虑使用单例.
 */
public class UserCloudApi extends ServiceCaller {

    private Service service;

    public UserCloudApi() {
        service = retrofit.create(Service.class);
    }


    /**
     * 更新用户信息.
     *
     * @param userModel
     */
    public void updateUserInfo(UserModel userModel, Callback<UserModel> callback) {

        Call<UserModel> caller = service.updateUserInfo(userModel.getUserId(), userModel);

        caller.enqueue(callback);
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
                        LogControl.debug("UserCloudApi", "Get sms code success");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        //请求失败
                        LogControl.debug("UserCloudApi", "Get sms code failed");
                        LogControl.debug("UserCloudApi", "Get sms code failed: " + t.getMessage());
                        t.printStackTrace();
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
    public void signIn(String phone, String developerId, String smsCode, Callback<SignInResult> callback) {
        //todo 检查各个参数的值
        QuickSignIn quickSignIn = new QuickSignIn(phone, developerId, smsCode);
        LogControl.debug("SignIn", quickSignIn.toString());

        Call<SignInResult> caller = service.quickSignIn(quickSignIn);

        //异步发起请求，所有的网络请求都需要异步发起请求
        caller.enqueue(callback);
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
        Call<UserModel> getUserInfo(@Path("id") String id);

        @PUT("/v1/users/{id}")
        Call<UserModel> updateUserInfo(@Path("id") String id, @Body UserModel userModel);

        @POST("/v1/users/validationCodes")
        Call<Void> getSmsCode(@Query("phoneNumber") String phone);

        @POST("/v1/users/sign")
        Call<SignInResult> quickSignIn(@Body QuickSignIn quickSignIn);
    }
}