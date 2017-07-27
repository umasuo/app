package com.umasuo.eva.ui.device.contoller;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created by umasuo on 17/7/6.
 * 照明设备，例如电灯泡.
 */
public class BulbController extends FragmentRoot implements View.OnClickListener {
    private static final String TAG = "BulbController";

    private MainActivity mainActivity;

    private ImageView backImg;
    private ImageView iconImg;
    private ImageView whiteImg;
    private ImageView timerImg;
    private ImageView colorImg;
    private ImageView sceneImg;

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
        View view = inflater.inflate(R.layout.device_ctr_bulb, container, false);
        mainActivity = (MainActivity) getContext();

        backImg = (ImageView) view.findViewById(R.id.back);
        backImg.setOnClickListener(this);

        iconImg = (ImageView) view.findViewById(R.id.icon_img);
        iconImg.setOnClickListener(this);

        whiteImg = (ImageView) view.findViewById(R.id.white_img);
        whiteImg.setOnClickListener(this);

        timerImg = (ImageView) view.findViewById(R.id.timer_img);
        timerImg.setOnClickListener(this);

        colorImg = (ImageView) view.findViewById(R.id.color_img);
        colorImg.setOnClickListener(this);

        sceneImg = (ImageView) view.findViewById(R.id.scene_img);
        sceneImg.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                mainActivity.popBackStack();
                break;
            }
            case R.id.white_img: {
                changeToWhite();
                break;
            }
            case R.id.timer_img: {
                Toast toast = Toast.makeText(mainActivity, "体验中心暂时不支持此功能", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            }
            case R.id.color_img: {
                changeToColor();
                break;
            }
            case R.id.scene_img: {
                Toast toast = Toast.makeText(mainActivity, "体验中心暂时不支持此功能", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            }
        }
    }

    private void changeToWhite() {
        iconImg.setImageResource(R.drawable.device_ctr_img_white_bulb_bg);
        whiteImg.setImageResource(R.drawable.device_ctr_img_white_bulb_on);
        colorImg.setImageResource(R.drawable.device_ctr_img_color_bulb_on);
    }

    private void changeToColor() {
        iconImg.setImageResource(R.drawable.device_ctr_img_color_bulb_bg);
        whiteImg.setImageResource(R.drawable.device_ctr_img_white_bulb_off);
        colorImg.setImageResource(R.drawable.device_ctr_img_color_bulb_off);
    }
}
