package com.umasuo.eva.ui.device.contoller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created by umasuo on 17/7/6.
 * 空气净化器。
 */
public class AirCleanerController extends FragmentRoot {
    private static final String TAG = "AirCleanerController";
    private MainActivity mainActivity;

    private boolean isPowerUp = false;
    private boolean isSimulator;
    private String deviceId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        LogControl.debug(TAG, "on create.");
        Bundle bundle = getArguments();
        isSimulator = (boolean) bundle.get("isSimulator");
        if (!isSimulator) {
            //在非体验的情况下显示这个
            deviceId = (String) bundle.get("deviceId");
        }

        View view = inflater.inflate(R.layout.device_ctr_air_cleaner, container, false);
        mainActivity = (MainActivity) getContext();

        return view;
    }
}
