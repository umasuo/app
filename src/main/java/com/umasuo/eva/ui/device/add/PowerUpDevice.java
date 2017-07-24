package com.umasuo.eva.ui.device.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.ui.device.DeviceItem;

/**
 * Created by liubin8095 on 2017/7/2.
 * 添加设备第二步：提醒用户确认设备已经开启，相关指示灯已经亮起.
 */
public class PowerUpDevice extends FragmentRoot implements View.OnClickListener {

    private String TAG = "PowerUpDevice";
    private ImageView backBtn;
    private Button nextBtn;
    MainActivity mActivity;
    private InputWifiPassword inputWifiPassword;
    private Bundle bundle;
    private DeviceItem deviceItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mActivity = (MainActivity) getContext();
        bundle = getArguments();
        deviceItem = (DeviceItem) bundle.get("model");
        LogControl.debug(TAG, "onCreateView name = " + deviceItem.getDeviceName());

        View view = inflater.inflate(R.layout.device_add_power_up, container, false);
        backBtn = (ImageView) view.findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        nextBtn = (Button) view.findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                // 回退
                mActivity.popBackStack();
                break;
            case R.id.next_btn:
                //下一步
                if (inputWifiPassword == null) {
                    inputWifiPassword = new InputWifiPassword();
                    inputWifiPassword.setPreIndex(index);
                    inputWifiPassword.setIndex(mActivity.getPagerSize());
                }
                mActivity.showFragment(this, inputWifiPassword, true);
        }

    }

}