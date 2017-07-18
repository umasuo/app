package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created on 2017/7/7.
 * 用户登录界面
 */
public class ForgotPassword extends FragmentRoot implements View.OnClickListener {


    // 以下是以此页面为入口的子页面
    private SigninWithSms signinWithSms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin_forgot_pwd, container, false);

        return view;
    }

    @Override
    public void onClick(View view) {
        // 界面上的点击事件
    }
}
