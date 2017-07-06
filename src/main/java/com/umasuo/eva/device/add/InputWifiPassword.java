package com.umasuo.eva.device.add;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.R;

/**
 * 添加设备第三步：输入连接的Wi-Fi密码.
 */
public class InputWifiPassword extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.device_add_input_wifi_pwd, container,false);
    }
}