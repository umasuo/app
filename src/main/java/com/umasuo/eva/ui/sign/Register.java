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
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created on 2017/7/7.
 * 用户登录界面
 */
public class Register extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "Register";

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
        View view = inflater.inflate(R.layout.signin_register, container, false);
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
