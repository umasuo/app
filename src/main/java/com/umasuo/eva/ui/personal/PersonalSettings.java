package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_settings, container, false);
        activity = (MainActivity) getContext();

        backImg = (ImageView) view.findViewById(R.id.back);
        backImg.setOnClickListener(this);

        return view;
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onClick(View view) {
    }
}
