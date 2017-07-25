package com.umasuo.eva.ui.simulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * 个人中心界面
 */

public class SimulatorCenter extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "PersonalCenter";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.simulator, container, false);
        //init data
        return view;
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onClick(View view) {
        LogControl.debug(TAG, "Test MQTT");

    }
}