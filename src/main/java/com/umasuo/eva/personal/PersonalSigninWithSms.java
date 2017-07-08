package com.umasuo.eva.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.R;

/**
 * Created on 2017/7/7.
 * 通过手机号、短信登录界面
 */
public class PersonalSigninWithSms extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_signin_sms, container, false);
        return view;
    }
}
