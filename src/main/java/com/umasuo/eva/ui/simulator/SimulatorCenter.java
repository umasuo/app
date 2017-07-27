package com.umasuo.eva.ui.simulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.ui.device.contoller.PowerStripController;

/**
 * 体验中心界面，用于操作虚拟设备.
 */
public class SimulatorCenter extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "PersonalCenter";
    private MainActivity mainActivity;

    private ImageView powerStrip;
    private PowerStripController powerStripController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.simulator, container, false);
        mainActivity = (MainActivity) getContext();

        powerStrip = (ImageView) view.findViewById(R.id.power_strip);
        powerStrip.setOnClickListener(this);
        //init data
        return view;
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.power_strip: {
                if (powerStripController == null) {
                    powerStripController = new PowerStripController();
                    powerStripController.setIndex(mainActivity.getPagerSize());
                    powerStripController.setPreIndex(index);
                }
                mainActivity.showFragment(this, powerStripController, true);
                break;
            }
        }
    }
}