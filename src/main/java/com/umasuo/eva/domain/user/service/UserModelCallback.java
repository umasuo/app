package com.umasuo.eva.domain.user.service;

import android.app.Activity;
import android.content.Context;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.infra.log.LogControl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by umasuo on 17/7/8.
 * 用户登录的回调接口
 */
public class UserModelCallback implements Callback<UserModel> {

    private static final String TAG = "UserModelCallback";
    private Context context;
    private UserService userService;

    public UserModelCallback(Context context, UserService userService) {
        this.context = context;
        this.userService = userService;
    }

    @Override
    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
        if (response.code() == 200) {
            //请求成功
            LogControl.debug("UserCloudApi", " sign Success");
            UserModel userModel = response.body();

            // 保存数据到数据库
            userService.saveUser(userModel);
            LogControl.debug(TAG, "userModel: " + userModel.toString());
            // 存起来，然后返回到主界面
            final Activity activity = (Activity) context;
            activity.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            ((MainActivity) context).showPage(2);
                        }
                    }
            );
        } else {
            // TODO: 17/7/18 处理请求失败的情况
        }
    }

    @Override
    public void onFailure(Call<UserModel> call, Throwable t) {
        //请求失败
        LogControl.debug("UserCloudApi", "failed");
        // TODO: 17/7/8 显示错误信息
    }
}
