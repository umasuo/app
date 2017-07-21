package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created on 2017/7/7.
 * 忘记密码，用户输入手机号，点击确定，然后服务端发送短信，然后用短信、手机号重置密码.
 */
public class ForgotPassword extends FragmentRoot implements View.OnClickListener {

    private EditText phoneText;
    private Button submitBtn;

    // 以下是以此页面为入口的子页面
    private SigninWithSms signinWithSms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin_forgot_pwd, container, false);

        phoneText = (EditText) view.findViewById(R.id.phone_text);
        submitBtn = (Button) view.findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_btn:
                forgetPassword();
                break;
        }
    }

    /**
     * 忘记密码提交。
     */
    public void forgetPassword() {
        Toast.makeText(getContext(), "你真的忘记密码了？", Toast.LENGTH_LONG).show();
        // TODO: 17/7/21 提交到服务器，服务器检查用户是否存在，如果不存在则不能够进入下一步.
    }
}
