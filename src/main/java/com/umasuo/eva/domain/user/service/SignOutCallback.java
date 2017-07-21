package com.umasuo.eva.domain.user.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.umasuo.eva.infra.log.LogControl;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by umasuo on 17/7/8.
 * 用户注册的毁掉借口
 */
public class SignOutCallback implements Callback<ResponseBody> {

    private static final String TAG = "RegisterCallback";
    private Context context;
    private UserService userService;

    public SignOutCallback(Context context, UserService userService) {
        this.context = context;
        this.userService = userService;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        // 不管如何，先把本地退出了。
        LogControl.debug("UserCloudApi", " register Success");
        // 清除数据库的数据
        userService.deleteTable();
        final Activity activity = (Activity) context;
        activity.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClassName(activity, "com.umasuo.eva.ui.sign.SignActivity");//用户登录成功，显示主界面
                        activity.startActivity(intent);
                        activity.finish();
                    }
                }
        );
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        //请求失败
        LogControl.debug("UserCloudApi", "failed");
        Toast toast = Toast.makeText(context, "退出登录失败，请重试.", Toast.LENGTH_LONG);
        toast.show();
    }
}
