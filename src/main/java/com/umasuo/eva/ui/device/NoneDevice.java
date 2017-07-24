package com.umasuo.eva.ui.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.ui.device.add.SelectDeviceType;

/**
 * 没有设备的页面
 */
public class NoneDevice extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "NoneDevice";

    private MainActivity activity;
    private Button addBtn;
    private SelectDeviceType selectDeviceType;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        LogControl.debug(TAG, "on create.");
        View view = inflater.inflate(R.layout.device_none, container, false);
        activity = (MainActivity) getContext();

        addBtn = (Button) view.findViewById(R.id.add_btn);
        addBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onClick(View view) {
        if (selectDeviceType == null) {
            selectDeviceType = new SelectDeviceType();
            selectDeviceType.setPreIndex(index);
            selectDeviceType.setIndex(activity.getPagerSize());
        }
        activity.showFragment(this, selectDeviceType, true);
    }
}