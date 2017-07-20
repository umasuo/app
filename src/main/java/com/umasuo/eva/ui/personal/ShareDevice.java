package com.umasuo.eva.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created by umasuo on 17/7/20.
 * 分享设备.
 */
public class ShareDevice extends FragmentRoot {

    private static final String TAG = "ShareDevice";
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogControl.debug(TAG, "onCreateView >>>");
        View view = inflater.inflate(R.layout.share_device, container, false);

        return view;
    }

    @Override
    public void onShow() {
        mainActivity.hideBottom();
    }

}
