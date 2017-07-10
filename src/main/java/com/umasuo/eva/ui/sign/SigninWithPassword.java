package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created on 2017/7/7.
 * 用户登录界面
 */
public class SigninWithPassword extends FragmentRoot implements View.OnClickListener {

    ImageView back;
    Button signin;
    TextView smsSign;
    TextView forgetPwd;


    // 以下是以此页面为入口的子页面
    private SigninWithSms signinWithSms;
    private ForgotPassword forgotPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_signin, container, false);

        back = view.findViewById(R.id.personal_signin_back);

        signin = view.findViewById(R.id.personal_signin_okBtn);

        smsSign = view.findViewById(R.id.personal_sms_signin);
        forgetPwd = view.findViewById(R.id.personal_forgot_password);

        back.setOnClickListener(this);
        signin.setOnClickListener(this);
        smsSign.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);

        return view;
    }

    /**
     * 响应界面上的点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        SignActivity activity = (SignActivity) this.getContext();
        switch (view.getId()) {
            case R.id.personal_signin_back: {
                activity.replaceFragment(preIndex);
                break;
            }
            case R.id.personal_signin_okBtn: {
                // TODO: 17/7/10 登录
                break;
            }
            case R.id.personal_sms_signin: {
                if (signinWithSms == null) {
                    signinWithSms = new SigninWithSms();
                    signinWithSms.setIndex(activity.addFragment(signinWithSms));
                    signinWithSms.setPreIndex(index);
                }
                activity.replaceFragment(signinWithSms.getIndex());
                break;
            }
            case R.id.personal_forgot_password: {
                if (forgotPassword == null) {
                    forgotPassword = new ForgotPassword();
                    forgotPassword.setIndex(activity.addFragment(forgotPassword));
                    forgotPassword.setPreIndex(index);
                }
                activity.replaceFragment(forgotPassword.getIndex());
                break;
            }
        }
    }
}
