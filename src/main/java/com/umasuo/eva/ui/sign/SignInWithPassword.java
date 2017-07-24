package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.umasuo.eva.R;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created on 2017/7/7.
 * 用户登录界面
 */
public class SignInWithPassword extends FragmentRoot implements View.OnClickListener {
    private static final String TAG = "SignInWithPassword";

    private TextView phoneText;
    private TextView passwordText;
    private TextView smsSign;
    private TextView forgetPwd;
    private Button submitBtn;
    private TextView registerText;


    // 以下是以此页面为入口的子页面
    private SigninWithSms signinWithSms;
    private ForgotPassword forgotPassword;
    private Register register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin_password, container, false);

//        back = view.findViewById(R.id.personal_signin_back);

        submitBtn = view.findViewById(R.id.submit_btn);

        phoneText = (EditText) view.findViewById(R.id.phone_text);
        passwordText = (EditText) view.findViewById(R.id.password_text);

        smsSign = view.findViewById(R.id.signin_sms_text);
        forgetPwd = view.findViewById(R.id.forgot_password_text);
        registerText = view.findViewById(R.id.register_text);

//        back.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        smsSign.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
        registerText.setOnClickListener(this);
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
            case R.id.submit_btn: {
                login();
                break;
            }
            case R.id.signin_sms_text: {
                if (signinWithSms == null) {
                    signinWithSms = new SigninWithSms();
                    signinWithSms.setIndex(activity.addFragment(signinWithSms));
                    signinWithSms.setPreIndex(index);
                }
                activity.replaceFragment(signinWithSms.getIndex());
                break;
            }
            case R.id.forgot_password_text: {
                if (forgotPassword == null) {
                    forgotPassword = new ForgotPassword();
                    forgotPassword.setIndex(activity.addFragment(forgotPassword));
                    forgotPassword.setPreIndex(index);
                }
                activity.replaceFragment(forgotPassword.getIndex());
                break;
            }
            case R.id.register_text: {
                if (register == null) {
                    register = new Register();
                    register.setIndex(activity.addFragment(register));
                    register.setPreIndex(index);
                }
                activity.replaceFragment(register.getIndex());
                break;
            }
        }
    }

    /**
     * login with phone and password
     *
     * @return
     */
    private void login() {
        String phone = phoneText.getText().toString();
        String password = passwordText.getText().toString();
        if (phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "手机号或者密码不能为空!", Toast.LENGTH_LONG).show();
            return;
        }

        UserService.getInstance(getContext()).login(phone, password);
    }
}
