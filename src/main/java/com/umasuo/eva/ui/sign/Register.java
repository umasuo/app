package com.umasuo.eva.ui.sign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created on 2017/7/7.
 * 用户登录界面
 */
public class Register extends FragmentRoot {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_register, container, false);
        return view;
    }
}
