package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.umasuo.eva.R;

/**
 * Created on 2017/7/7.
 * 用户未登录的界面
 */
public class SigninStarter extends Fragment implements View.OnClickListener {

    private String TAG = "SigninStarter";

    Button signinBtn;
    Button signupBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_signin_starter, container, false);
        signinBtn = (Button) view.findViewById(R.id.signin_starter_signin_btn);
        signupBtn = (Button) view.findViewById(R.id.signin_starter_signup_btn);

        signinBtn.setOnClickListener(this);
        return view;
    }

    /**
     * 按钮点击事件.
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signin_starter_signin_btn:
                //显示登录界面
                break;
            case R.id.signin_starter_signup_btn:
                //显示注册界面
                break;
        }

    }
}
