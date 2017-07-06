package com.umasuo.eva.device.add;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.umasuo.eva.R;
import com.umasuo.eva.tools.log.LogControl;

/**
 * Created by liubin8095 on 2017/7/2.
 * 添加设备第二步：提醒用户确认设备已经开启，相关指示灯已经亮起.
 */
public class PowerUpDevice extends Fragment implements View.OnClickListener {

    private String TAG = "PowerUpDevice";
    private ImageView backBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.device_add_power_up, container, false);
        backBtn = (ImageView) view.findViewById(R.id.device_power_up_back);
        backBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        ((AddDeviceActivity) this.getContext()).replaceFragment(0, "null");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogControl.debug(TAG, "attach");
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            LogControl.debug(TAG, String.valueOf(bundle.get("name")));
        }
        LogControl.debug(TAG, "resume");

    }

    @Override
    public void onPause() {
        super.onPause();
        LogControl.debug(TAG, "pause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogControl.debug(TAG, "destroy");
    }
}