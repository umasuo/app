package com.umasuo.eva.ui.device.add;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.domain.device.dto.ProductTypeModel;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * 添加设备第三步：输入连接的Wi-Fi密码.
 */
public class InputWifiPassword extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "InputWifiPassword";

    private ImageView backImg;
    private TextView curWifi;
    private EditText passwordEdit;
    private Button nextBtn;
    private MainActivity mainActivity;
    private ConnectingDevice connectingDevice;

    private ProductTypeModel productType;
    private String ssid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.device_add_input_wifi_pwd, container, false);
        mainActivity = (MainActivity) getContext();

        Bundle bundle = getArguments();
        productType = (ProductTypeModel) bundle.get("productType");
        LogControl.debug(TAG, "onCreateView name = " + productType.name);

        backImg = (ImageView) view.findViewById(R.id.back);
        backImg.setOnClickListener(this);

        curWifi = (TextView) view.findViewById(R.id.current_wifi_text);
        passwordEdit = (EditText) view.findViewById(R.id.password_text);
        nextBtn = (Button) view.findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(this);
        getCurWifi();

        return view;
    }

    /**
     * 获取当前连接的Wi-Fi名.
     */
    private void getCurWifi() {
        ConnectivityManager connMgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
            WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(getContext().getApplicationContext().WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            ssid = wifiInfo.getSSID();
            curWifi.setText(curWifi.getText() + ": " + ssid);
            nextBtn.setEnabled(true);
            LogControl.debug(TAG, "SSID: " + ssid);
        } else {
            nextBtn.setEnabled(false);
            LogControl.debug(TAG, "Do not connected to wifi.");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                mainActivity.popBackStack();
                break;
            }
            case R.id.next_btn: {
                if (connectingDevice == null) {
                    connectingDevice = new ConnectingDevice();
                    connectingDevice.setPreIndex(index);
                    connectingDevice.setIndex(mainActivity.getPagerSize());
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("productType", productType);
                bundle.putSerializable("ssid", ssid);
                bundle.putSerializable("password", passwordEdit.getText().toString());
                connectingDevice.setArguments(bundle);
                mainActivity.showFragment(this, connectingDevice, true);
            }
        }

    }
}