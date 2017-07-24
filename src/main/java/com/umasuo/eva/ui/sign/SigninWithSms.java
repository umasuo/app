package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.umasuo.eva.R;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created on 2017/7/7.
 * 通过手机号、短信登录界面
 */
public class SigninWithSms extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "SigninWithSms";

    private SignActivity signActivity;

    private ImageView backBtn;

    private Button signin;

    private Button smsCodeBtn;

    private EditText phoneText;

    private EditText smsCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin_sms, container, false);
        signActivity = (SignActivity) getContext();

        backBtn = (ImageView) view.findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        smsCodeBtn = (Button) view.findViewById(R.id.sms_get_btn);
        smsCodeBtn.setOnClickListener(this);

        signin = (Button) view.findViewById(R.id.submit_btn);
        signin.setOnClickListener(this);

        phoneText = (EditText) view.findViewById(R.id.phone_text);
        smsCode = (EditText) view.findViewById(R.id.sms_code);

        return view;
    }

    @Override
    public void onClick(View view) {
        final SignActivity activity = (SignActivity) this.getContext();
        switch (view.getId()) {
            case R.id.back: {
                //返回上一级页面
                activity.replaceFragment(preIndex);
                break;
            }
            case R.id.submit_btn: {
                String phoneText = this.phoneText.getText().toString();
                String smsCodeText = smsCode.getText().toString();
                // TODO: 17/7/18 更改开发者ID
                UserService.getInstance(signActivity).signinWithSmsCode(phoneText, smsCodeText, "developer1");
                break;
            }
            case R.id.sms_get_btn: {
                // 发起发送短信验证码请求
                String phoneText = this.phoneText.getText().toString();
                LogControl.debug(TAG, "Get sms code for: " + phoneText);

                UserService.getInstance(signActivity).getSmsCode(phoneText);//调用业务逻辑服务，然后再在界面上进行更改.
                smsCodeBtn.setText("发送成功");
                smsCodeBtn.setEnabled(false);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            this.sleep(60000);//等待60秒，然后重新更改为重新发送和可用
                            activity.runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            smsCodeBtn.setText("重新发送");
                                            smsCodeBtn.setEnabled(true);
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

    /**
     * 获取注册用的sms code.
     */
    private void getSmsCode() {
        String phone = phoneText.getText().toString();
        LogControl.debug(TAG, "Get sms code for: " + phone);
        if (phone.isEmpty()) {
            Toast.makeText(signActivity, "请输入手机号", Toast.LENGTH_SHORT).show();
        } else {
            UserService.getInstance(signActivity).getSmsCode(phone);//调用业务逻辑服务，然后再在界面上进行更改.
            smsCodeBtn.setText("发送成功");
            smsCodeBtn.setEnabled(false);
            new Thread() {
                @Override
                public void run() {
                    try {
                        this.sleep(60000);//等待60秒，然后重新更改为重新发送和可用
                        signActivity.runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        smsCodeBtn.setText("重新发送");
                                        smsCodeBtn.setEnabled(true);
                                    }
                                }
                        );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

    }
}
