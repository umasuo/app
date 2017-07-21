package com.umasuo.eva.ui.device.contoller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created by umasuo on 17/7/6.
 * 锁，门锁.
 */
public class LockController extends FragmentRoot {
    private static final String TAG = "AirCleanerController";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        LogControl.debug(TAG, "on create.");
        View view = inflater.inflate(R.layout.device_ctr_switch, container, false);

        return view;
    }
}
