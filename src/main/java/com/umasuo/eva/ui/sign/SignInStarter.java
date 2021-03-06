package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created on 2017/7/7.
 * 用户未登录的界面
 */
public class SignInStarter extends FragmentRoot implements View.OnClickListener {

    private String TAG = "SignInStarter";

    Button signinBtn;
    Button registerBtn;

    SignInWithPassword signinWithPassword;
    Register register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin_starter, container, false);
        signinBtn = (Button) view.findViewById(R.id.signin_btn);
        registerBtn = (Button) view.findViewById(R.id.register_text);

        signinBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        return view;
    }

    /**
     * 按钮点击事件.
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        LogControl.debug(TAG, String.valueOf(view.getId()));
        switch (view.getId()) {
            case R.id.signin_btn: {
                //显示登录界面
                LogControl.debug(TAG, "sign");
                if (signinWithPassword == null) {
                    signinWithPassword = new SignInWithPassword();
                    signinWithPassword.setPreIndex(0);
                    signinWithPassword.setIndex(((SignActivity) this.getContext()).addFragment(signinWithPassword));
                }
                ((SignActivity) this.getContext()).replaceFragment(signinWithPassword.getIndex());
                break;
            }
            case R.id.register_text: {
                //显示注册界面
                LogControl.debug(TAG, "register");
                if (register == null) {
                    register = new Register();
                    register.setPreIndex(0);
                    register.setIndex(((SignActivity) this.getContext()).addFragment(register));
                }
                ((SignActivity) this.getContext()).replaceFragment(register.getIndex());
                break;
            }
        }
    }

}
