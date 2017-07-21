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
 * 用户注册的毁掉借口
 */
public class RegisterCallback implements Callback<SignInResult> {

    private static final String TAG = "RegisterCallback";
    private Context context;
    private UserService userService;

    public RegisterCallback(Context context, UserService userService) {
        this.context = context;
        this.userService = userService;
    }

    @Override
    public void onResponse(Call<SignInResult> call, Response<SignInResult> response) {
        //请求成功
        if (response.code() == 200) {
            LogControl.debug("UserCloudApi", " register Success");
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
                            // TODO: 17/7/21 显示一个toast
                            Toast toast = Toast.makeText(context, "注册成功，自动登录", Toast.LENGTH_LONG);
                            toast.show();

                            Intent intent = new Intent();
                            intent.setClassName(activity, "com.umasuo.eva.MainActivity");//用户登录成功，显示主界面
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    }
            );
        } else {
            // TODO: 17/7/18 处理失败的情况
            Toast toast = Toast.makeText(context, "注册失败，请重试.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onFailure(Call<SignInResult> call, Throwable t) {
        //请求失败
        LogControl.debug("UserCloudApi", "failed");
        Toast toast = Toast.makeText(context, "注册失败，请重试.", Toast.LENGTH_LONG);
        toast.show();
    }
}
