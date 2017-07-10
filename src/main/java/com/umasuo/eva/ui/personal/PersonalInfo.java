package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created on 2017/7/7.
 * 个人信息设置界面
 */
public class PersonalInfo extends FragmentRoot {

    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_info, container, false);
        activity = (MainActivity) getContext();

        onShow();
        return view;
    }

    @Override
    public void onShow() {
        if (activity != null) {
            activity.hideBottom();
        }
    }

}
