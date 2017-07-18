package com.umasuo.eva.ui.device.add;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * 添加设备第三步：输入连接的Wi-Fi密码.
 */
public class InputWifiPassword extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "InputWifiPassword";

    TextView curWifi;
    Button nextBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.device_add_input_wifi_pwd, container, false);

        curWifi = (TextView) view.findViewById(R.id.current_wifi_text);
        nextBtn = (Button) view.findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(this);
        getCurWifi();

        return view;
    }

    private void getCurWifi() {

        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(getContext().getApplicationContext().WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ssid = wifiInfo.getSSID();
        LogControl.debug(TAG, ssid);
        curWifi.setText(curWifi.getText() + ": " + ssid);
    }

    @Override
    public void onClick(View view) {
        // TODO: 17/7/18 跳转到开始连接发包的界面
    }
}