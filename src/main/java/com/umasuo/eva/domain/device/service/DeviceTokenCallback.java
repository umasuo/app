package com.umasuo.eva.domain.device.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.umasuo.eva.domain.user.dto.SignInResult;
import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.domain.user.dto.mapper.UserMapper;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.log.LogControl;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by umasuo on 17/7/8.
 * 获取设备配网token的结果.这个可能没有啥用了.
 */
public class DeviceTokenCallback implements Callback<ResponseBody> {

    private static final String TAG = "DeviceTokenCallback";
    private Context context;
    private DeviceService deviceService;

    public DeviceTokenCallback(Context context, DeviceService deviceService) {
        this.context = context;
        this.deviceService = deviceService;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        //请求成功
        LogControl.debug("UserCloudApi", " sign Success");
        ResponseBody result = response.body();
        String token = null;
        try {
            token = result.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 保存数据到数据库
        deviceService.setToken(token);

        LogControl.debug(TAG, "Device token: " + token);

        // 存起来，然后返回到主界面
        final Activity activity = (Activity) context;
        activity.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        // TODO: 17/7/11  停止转圈圈
                    }
                }
        );
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        //请求失败
        LogControl.debug(TAG, "Failed.");
        // TODO: 17/7/8 显示错误信息
        t.printStackTrace();

    }
}
