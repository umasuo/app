package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.domain.user.dto.UserModel;
import com.umasuo.eva.domain.user.service.UserService;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created on 2017/7/7.
 * 个人信息设置界面
 */
public class PersonalInfo extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "PersonalInfo";
    private MainActivity activity;
    private ImageView backBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogControl.debug(TAG, "onCreateView >>>");
        View view = inflater.inflate(R.layout.personal_info, container, false);
        activity = (MainActivity) getContext();

        backBtn = (ImageView) view.findViewById(R.id.back);


        backBtn.setOnClickListener(this);

        initData();
        return view;
    }

    private void initData() {
        UserModel userModel = UserService.getInstance(getContext()).getUser();
        if (userModel != null) {
            // TODO: 17/7/25 设置用户信息
        }
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                activity.popBackStack();
                break;
            }
        }
    }

}
