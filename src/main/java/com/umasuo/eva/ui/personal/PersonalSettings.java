package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * Created on 2017/7/7.
 * 个人中心设置界面
 */
public class PersonalSettings extends FragmentRoot implements View.OnClickListener {

    private MainActivity activity;
    private TextView closeBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_settings, container, false);

        closeBtn = (TextView) view.findViewById(R.id.close_page);
        closeBtn.setOnClickListener(this);


        activity = (MainActivity) getContext();
        return view;
    }

    @Override
    public void onShow() {
        activity.hideBottom();
    }

    @Override
    public void onClick(View view) {
        activity.showPage(preIndex);
    }
}
