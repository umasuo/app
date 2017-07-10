package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.R;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.infra.server.user.UserServerApi;

/**
 * Created on 2017/7/7.
 * 通过手机号、短信登录界面
 */
public class SigninWithSms extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "SigninWithSms";

    private ImageView backBtn;

    private Button signin;

    private Button getSmsCodeBtn;

    private EditText phone;

    private EditText smsCode;

    private UserService userService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_signin_sms, container, false);

        backBtn = (ImageView) view.findViewById(R.id.personal_signin_sms_back);
        backBtn.setOnClickListener(this);

        getSmsCodeBtn = (Button) view.findViewById(R.id.personal_signin_sms_get);
        getSmsCodeBtn.setOnClickListener(this);

        signin = (Button) view.findViewById(R.id.personal_signin_ok);
        signin.setOnClickListener(this);

        phone = (EditText) view.findViewById(R.id.personal_signin_phone);
        smsCode = (EditText) view.findViewById(R.id.personal_signin_sms_code);

        userService = new UserService(getContext());

        return view;
    }

    @Override
    public void onClick(View view) {
        final SignActivity activity = (SignActivity) this.getContext();
        switch (view.getId()) {
            case R.id.personal_signin_sms_back: {
                //返回上一级页面
                activity.replaceFragment(preIndex);
                break;
            }
            case R.id.personal_signin_ok: {
                String phoneText = phone.getText().toString();
                String smsCodeText = smsCode.getText().toString();
                userService.signinWithSmsCode(phoneText, smsCodeText, "developer1");
                break;
            }
            case R.id.personal_signin_sms_get: {
                // 发起发送短信验证码请求
                String phoneText = phone.getText().toString();
                LogControl.debug(TAG, "Get sms code for: " + phoneText);
                userService.getSmsCode(phoneText);//调用业务逻辑服务，然后再在界面上进行更改.
                getSmsCodeBtn.setText("发送成功");
                getSmsCodeBtn.setEnabled(false);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            this.sleep(60000);//等待60秒，然后重新更改为重新发送和可用
                            activity.runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            getSmsCodeBtn.setText("重新发送");
                                            getSmsCodeBtn.setEnabled(true);
                                        }
                                    }
                            );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                break;
            }
        }

    }
}
