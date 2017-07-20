package com.umasuo.eva.ui.device.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;

/**
 * 添加设备第四步：给设备发送网络包.
 */
public class ConnectingDevice extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "ConnectingDevice";

    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.device_add_connecting, container, false);

        mainActivity = (MainActivity) getContext();

        return view;
    }

    @Override
    public void onClick(View view) {
        // TODO: 17/7/18 跳转到开始连接发包的界面
    }
}