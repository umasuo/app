package com.umasuo.eva.ui.device.contoller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created by umasuo on 17/7/6.
 * 微波炉
 */
public class MicrowaveOvenController extends FragmentRoot implements View.OnClickListener {
    private static final String TAG = "MicrowaveOvenController";
    private MainActivity mainActivity;
    private boolean isPowerUp = false;
    private boolean isSimulator;
    private String deviceId;

    private ImageView backImg;

    private ImageView powerCtrImg;

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

        View view = inflater.inflate(R.layout.device_ctr_wbl, container, false);
        mainActivity = (MainActivity) getContext();


        backImg = (ImageView) view.findViewById(R.id.back);
        backImg.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                mainActivity.popBackStack();
                break;
            }
            case R.id.power_ctr_img: {
                power();
                break;
            }
        }
    }

    /**
     * 开关。
     */
    private void power() {
        if (isPowerUp) {
            powerCtrImg.setImageResource(R.drawable.device_ctr_img_power_gray_bg);
            isPowerUp = false;
        } else {
            powerCtrImg.setImageResource(R.drawable.device_ctr_img_power_blue_bg);
            isPowerUp = true;
        }
    }
}
