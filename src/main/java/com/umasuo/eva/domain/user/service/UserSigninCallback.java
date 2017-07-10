package com.umasuo.eva.domain.user.service;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.umasuo.eva.R;
import com.umasuo.eva.domain.user.dto.SignInResult;
import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.domain.user.dto.mapper.UserMapper;
import com.umasuo.eva.infra.database.DatabaseHelper;
import com.umasuo.eva.infra.database.UserEntity;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.infra.server.user.UserServerApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by umasuo on 17/7/8.
 * 用户登录的回调接口
 */
public class UserSigninCallback implements Callback<SignInResult> {

    private static final String TAG = "UserSigninCallback";
    private Context context;

    public UserSigninCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<SignInResult> call, Response<SignInResult> response) {
        //请求成功
        LogControl.debug("UserServerApi", " signin Success");
        SignInResult result = response.body();
        UserModel userModel = UserMapper.toModel(result);
        LogControl.debug(TAG, "userModel: " + userModel.toString());
        // 存起来，然后返回到主界面
        final Activity activity = (Activity) context;
        activity.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClassName(activity, "com.umasuo.eva.MainActivity");//用户登录成功，显示主界面
                        activity.startActivity(intent);
                        activity.finish();
                    }
                }
        );
    }

    @Override
    public void onFailure(Call<SignInResult> call, Throwable t) {
        //请求失败
        LogControl.debug("UserServerApi", "failed");
        // TODO: 17/7/8 显示错误信息
    }
}
