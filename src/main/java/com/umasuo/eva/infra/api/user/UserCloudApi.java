package com.umasuo.eva.infra.api.user;

import com.umasuo.eva.domain.user.dto.QuickSignIn;
import com.umasuo.eva.domain.user.dto.RegisterModel;
import com.umasuo.eva.domain.user.dto.SignIn;
import com.umasuo.eva.domain.user.dto.SignInResult;
import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.domain.user.service.RegisterCallback;
import com.umasuo.eva.infra.api.ServiceCaller;
import com.umasuo.eva.infra.log.LogControl;

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
     * login with password and phone.
     *
     * @param phone
     * @param developerId
     * @param password
     * @param callback
     */
    public void login(String phone, String developerId, String password, Callback<SignInResult> callback) {
        SignIn signIn = new SignIn(phone, developerId, password);
        LogControl.debug("SignIn", signIn.toString());

        Call<SignInResult> caller = service.login(signIn);

        //异步发起请求，所有的网络请求都需要异步发起请求
        caller.enqueue(callback);
    }

    /**
     * 登录的API.
     *
     * @param phone
     * @param developerId
     * @param password
     * @param smsCode
     * @param callback
     */
    public void register(String phone, String developerId, String password, String smsCode, Callback<SignInResult> callback) {
        //todo 检查各个参数的值
        RegisterModel registerModel = new RegisterModel(phone, developerId, password, smsCode);
        LogControl.debug("RegisterModel", registerModel.toString());

        Call<SignInResult> caller = service.register(registerModel);

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

        @POST("/v1/users/signin")
        Call<SignInResult> quickSignIn(@Body QuickSignIn quickSignIn);

        @POST("/v1/users/register")
        Call<SignInResult> register(@Body RegisterModel register);

        @POST("/v1/users/login")
        Call<SignInResult> login(@Body SignIn signIn);
    }
}