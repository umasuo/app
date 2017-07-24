package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.umasuo.eva.R;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created on 2017/7/7.
 * 忘记密码，用户输入手机号，点击确定，然后服务端发送短信，然后用短信、手机号重置密码.
 */
public class ForgotPassword extends FragmentRoot implements View.OnClickListener {

    private SignActivity signActivity;
    private Spinner countrySpinner;
    private ImageView backImg;
    private EditText phoneText;
    private EditText passwordText;
    private EditText password2Text;
    private EditText smsCodeText;
    private Button smsCodeBtn;
    private Button submitBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin_forgot_pwd, container, false);
        signActivity = (SignActivity) getContext();
        
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        backImg = (ImageView) view.findViewById(R.id.back);
        countrySpinner = (Spinner) view.findViewById(R.id.country_spinner);
        phoneText = (EditText) view.findViewById(R.id.phone_text);
        passwordText = (EditText) view.findViewById(R.id.password_text);
        password2Text = (EditText) view.findViewById(R.id.password_repeat_text);
        smsCodeText = (EditText) view.findViewById(R.id.sms_code);
        smsCodeBtn = (Button) view.findViewById(R.id.sms_get_btn);
        submitBtn = (Button) view.findViewById(R.id.submit_btn);
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        smsCodeBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                signActivity.replaceFragment(preIndex);
                break;
            case R.id.sms_get_btn:
                getSmsCode();
                break;
            case R.id.submit_btn:
                register();
                break;
        }
    }

    /**
     * 获取注册用的sms code.
     */
    private void getSmsCode() {
        String phone = phoneText.getText().toString();
        if (phone.isEmpty()) {
            Toast.makeText(signActivity, "请输入手机号", Toast.LENGTH_SHORT).show();
        } else {
            // TODO: 17/7/21 进入等待状态，请求返回之后再显示发送成功
            UserService.getInstance(getContext()).getSmsCode(phone);
        }
    }

    /**
     * 注册并登录.
     */
    private void register() {
        String phone = phoneText.getText().toString();
        String password = passwordText.getText().toString();
        String password2 = password2Text.getText().toString();
        String smsCode = smsCodeText.getText().toString();
        if (phone.isEmpty() || password.isEmpty() || password2.isEmpty() || smsCode.isEmpty()) {
            Toast toast = Toast.makeText(getContext(), "必填信息不能为空！", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        if (!password.equals(password2)) {
            Toast toast = new Toast(getContext());
            toast.setText("两次输入的密码不匹");
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        UserService.getInstance(getContext()).register(phone, password, smsCode);
    }
}
