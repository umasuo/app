package com.umasuo.eva.ui.device.contoller;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.log.LogControl;

/**
 * Created by umasuo on 17/7/6.
 * 洗衣机.
 */
public class WasherController extends FragmentRoot implements View.OnClickListener {
    private static final String TAG = "WasherController";

    private MainActivity mainActivity;

    //洗了多长时间了
    private TextView timeText;
    //水位文字
    private TextView waterLevelText;

    private ImageView backImg;
    private ImageView powerCtrImg;
    private ImageView waterLevelImg;
    private ImageView modelImg;
    private ImageView timerImg;
    // 漂洗次数
    private ImageView washTimesImg;

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
        View view = inflater.inflate(R.layout.device_ctr_washer, container, false);
        mainActivity = (MainActivity) getContext();


        timeText = (TextView) view.findViewById(R.id.time_text);
        waterLevelText = (TextView) view.findViewById(R.id.water_level_text);

        backImg = (ImageView) view.findViewById(R.id.back);
        backImg.setOnClickListener(this);

        powerCtrImg = (ImageView) view.findViewById(R.id.power_ctr_img);
        powerCtrImg.setOnClickListener(this);

        waterLevelImg = (ImageView) view.findViewById(R.id.water_level_img);
        waterLevelImg.setOnClickListener(this);

        modelImg = (ImageView) view.findViewById(R.id.model_img);
        modelImg.setOnClickListener(this);

        timerImg = (ImageView) view.findViewById(R.id.timer_img);
        timerImg.setOnClickListener(this);

        washTimesImg = (ImageView) view.findViewById(R.id.wash_times_img);
        washTimesImg.setOnClickListener(this);


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
            case R.id.water_level_img: {
                waterLevel();
                break;
            }
            case R.id.model_img: {
                waterLevel();
                break;
            }
            case R.id.timer_img: {
                waterLevel();
                break;
            }
            case R.id.wash_times_img: {
                waterLevel();
                break;
            }
        }
    }

    /**
     * 开关洗衣机。
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

    private void waterLevel() {
        Toast toast = Toast.makeText(mainActivity, "演示设备暂时不支持此功能！", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
