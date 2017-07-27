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
 * 开关控制器界面.
 */
public class SwitchController extends FragmentRoot implements View.OnClickListener {
    private static final String TAG = "SwitchController";

    private MainActivity mainActivity;

    private ImageView backImg;
    private ImageView powerImg;
    private ImageView powerCtrImg;
    private ImageView timerImg;

    private boolean isPowerUp = false;

    private boolean isSimulator;
    private String deviceId;

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
        View view = inflater.inflate(R.layout.device_ctr_switch, container, false);
        mainActivity = (MainActivity) getContext();


        backImg = (ImageView) view.findViewById(R.id.back);
        backImg.setOnClickListener(this);

        powerImg = (ImageView) view.findViewById(R.id.power_img);
        powerImg.setOnClickListener(this);

        powerCtrImg = (ImageView) view.findViewById(R.id.power_ctr_img);
        powerCtrImg.setOnClickListener(this);

        timerImg = (ImageView) view.findViewById(R.id.timer_img);
        timerImg.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                mainActivity.popBackStack();
                break;
            }
            case R.id.power_img:
            case R.id.power_ctr_img: {
                power();
                break;
            }
            case R.id.timer_img: {
                timer();
                break;
            }
        }
    }

    private void power() {
        if (isPowerUp) {
            powerImg.setImageResource(R.drawable.device_ctr_img_power_gray);
            powerCtrImg.setImageResource(R.drawable.device_ctr_img_power_gray);
            isPowerUp = false;
        } else {
            powerImg.setImageResource(R.drawable.device_ctr_img_power_blue);
            powerCtrImg.setImageResource(R.drawable.device_ctr_img_power_blue);
            isPowerUp = true;
        }
    }

    private void timer() {

    }
}
