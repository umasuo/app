package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class PersonalInfo extends FragmentRoot implements View.OnClickListener, TextWatcher {

    private static final String TAG = "PersonalInfo";
    private MainActivity activity;
    private ImageView backBtn;
    private ImageView headerIcon;
    private EditText userName;
    private EditText userPhone;
    private EditText userEmail;
    private EditText userAge;
    private EditText userCountry;
    private EditText userSignature;
    private Button userPassword;
    private Button submitBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogControl.debug(TAG,"onCreateView >>>");
        View view = inflater.inflate(R.layout.personal_info, container, false);
        activity = (MainActivity) getContext();

        backBtn = (ImageView) view.findViewById(R.id.back_btn);
        headerIcon = (ImageView) view.findViewById(R.id.user_head_icon);
        userName = (EditText) view.findViewById(R.id.user_name);
        userPhone = (EditText) view.findViewById(R.id.user_phone);
        userEmail = (EditText) view.findViewById(R.id.user_email);
        userAge = (EditText) view.findViewById(R.id.user_age);
        userCountry = (EditText) view.findViewById(R.id.user_country);
        userSignature = (EditText) view.findViewById(R.id.user_signature);
        userPassword = (Button) view.findViewById(R.id.user_password);

        submitBtn = (Button) view.findViewById(R.id.submit_btn);

        backBtn.setOnClickListener(this);
        headerIcon.setOnClickListener(this);
        userPassword.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        userName.addTextChangedListener(this);
        userPhone.addTextChangedListener(this);
        userEmail.addTextChangedListener(this);
        userAge.addTextChangedListener(this);
        userCountry.addTextChangedListener(this);
        userSignature.addTextChangedListener(this);


        initData();

        onShow();
        return view;
    }

    private void initData() {
        UserModel userModel = UserService.getInstance(getContext()).getUser();
        if (userModel != null) {
            userName.setText(userModel.getName());
            userPhone.setText(userModel.getPhone());
            userEmail.setText(userModel.getEmail());
            userAge.setText(String.valueOf(userModel.getAge()));
            userCountry.setText(userModel.getCountry());
            userSignature.setText(userModel.getSignature());
        }
    }

    @Override
    public void onShow() {
        if (activity != null) {
            LogControl.debug(TAG,"onShow >>>");
            activity.hideBottom();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn: {
                activity.showPage(preIndex);
                break;
            }
            case R.id.submit_btn: {
                // 提交信息到服务器和数据库
                LogControl.debug(TAG, "Submit change user info");
                String email = userEmail.getText().toString();
                String phone = userPhone.getText().toString();
                int age = Integer.valueOf(userAge.getText().toString());
                String country = userCountry.getText().toString();
                String name = userName.getText().toString();
                String signature = userSignature.getText().toString();
                UserService.getInstance(getContext()).updateUser(email, phone, age, country, name, signature);
                break;
            }
            case R.id.user_head_icon: {
                //更改用户头像
                LogControl.debug(TAG, "Change user's icon ");
                break;
            }
            case R.id.user_password: {
                //更改用户密码
                LogControl.debug(TAG, "Change user's password ");
                break;
            }
        }
    }

    /**
     * 文字更改之后，将提交按钮显示为可用.
     **/
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        submitBtn.setEnabled(true);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // do nothing
    }

    @Override
    public void afterTextChanged(Editable editable) {
        submitBtn.setEnabled(true);
    }
}
