package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created on 2017/7/7.
 * 个人中心设置界面
 */
public class PersonalSettings extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "PersonalSettings";
    private MainActivity activity;
    private ImageView backImg;
    private TextView saveText;


    private Button signoutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_settings, container, false);
        activity = (MainActivity) getContext();

        backImg = (ImageView) view.findViewById(R.id.back);
        backImg.setOnClickListener(this);


        saveText = (TextView) view.findViewById(R.id.save);
        saveText.setOnClickListener(this);


        signoutBtn = (Button) view.findViewById(R.id.sign_out_btn);
        signoutBtn.setOnClickListener(this);

        return view;
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
            case R.id.save: {
                //todo save settings
                activity.popBackStack();
                break;
            }
            case R.id.sign_out_btn: {
                break;
                // 退出登录，清空所有用户的信息和设置
            }
        }
    }
}
