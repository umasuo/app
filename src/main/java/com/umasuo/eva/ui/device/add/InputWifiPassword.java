package com.umasuo.eva.ui.device.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * 添加设备第三步：输入连接的Wi-Fi密码.
 */
public class InputWifiPassword extends FragmentRoot {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.device_add_input_wifi_pwd, container, false);
    }
}