package com.umasuo.eva.domain.user.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.umasuo.eva.domain.user.dto.SignInResult;
import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.domain.user.dto.mapper.UserMapper;
import com.umasuo.eva.infra.log.LogControl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by umasuo on 17/7/8.
 * 用户登录的回调接口
 */
public class SignInCallback implements Callback<SignInResult> {

    private static final String TAG = "SignInCallback";
    private Context context;
    private UserService userService;

    public SignInCallback(Context context, UserService userService) {
        this.context = context;
        this.userService = userService;
    }

    @Override
    public void onResponse(Call<SignInResult> call, Response<SignInResult> response) {
        //请求成功
        if (response.code() == 200) {
            LogControl.debug("UserCloudApi", " sign Success");
            SignInResult result = response.body();
            UserModel userModel = UserMapper.toModel(result);
            // 保存数据到数据库
            userService.saveUser(userModel);
            LogControl.debug(TAG, "userModel: " + userModel.toString());
            // 存起来，然后返回到主界面
            final Activity activity = (Activity) context;
            activity.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent();
                            intent.setClassName(activity, "com.umasuo.eva.MainActivity");//用户登录成功，显示主界面
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    }
            );
        } else {
            // TODO: 17/7/21 处理不同的错误情况
            Toast.makeText(context, "登录失败，请重试!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<SignInResult> call, Throwable t) {
        //请求失败
        LogControl.debug("UserCloudApi", "failed");
        Toast.makeText(context, "登录失败，请重试!", Toast.LENGTH_SHORT).show();
        // TODO: 17/7/8 显示错误信息
    }
}
