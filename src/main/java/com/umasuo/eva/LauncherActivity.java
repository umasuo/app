package com.umasuo.eva;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.log.LogControl;

/**
 * 启动页面，加载数据库的信息，看用户是否已经登录，如果已经登录，则跳转到主界面，否则进入登录、注册界面
 */
public class LauncherActivity extends Activity {

    private static final String TAG = "LauncherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.launcher);

        UserService userService = new UserService(this);
        UserModel user = userService.getUser();

        LogControl.debug(TAG, "user info: " + user.toString());

        if (user != null && user.getToken() != null && user.getUserId() != null) {
            //用户处于登录状态，跳转到主界面
            Intent intent = new Intent();
            intent.setClassName(this, "com.umasuo.eva.MainActivity");//打开一个主界面
            startActivity(intent);
            overridePendingTransition(R.anim.choose_open, R.anim.choose_close);
        } else {
            //用户未登录，跳转到登录界面
            Intent intent = new Intent();
            intent.setClassName(this, "com.umasuo.eva.ui.sign.SignActivity");//打开登录界面
            startActivity(intent);
            overridePendingTransition(R.anim.choose_open, R.anim.choose_close);
        }

        //跳转进去之后就不再显示此界面了，看看后面有没有其他需求需要重新用这个界面做缓冲.
        this.finish();

    }
}
