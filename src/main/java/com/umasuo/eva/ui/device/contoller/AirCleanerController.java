package com.umasuo.eva.ui.device.contoller;

import android.graphics.Color;
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
 * 空气净化器。
 */
public class AirCleanerController extends FragmentRoot implements View.OnClickListener {
    private static final String TAG = "AirCleanerController";
    private MainActivity mainActivity;


    private ImageView backImg;

    private TextView pmValueText;

    private TextView sleepText;
    private boolean isSleep = false;

    private TextView anionText;//负离子
    private boolean isAnion = false;

    private TextView childLockText;//负离子
    private boolean isChildLock = false;

    private TextView smartModelText;//负离子
    private boolean isSmartModel = false;

    private TextView uvText;//UV 杀菌
    private boolean isUv;

    private ImageView powerCtrImg;
    private ImageView humidifyImg;//加湿
    private ImageView modelImg;//模式选择
    private ImageView timerImg;//模式选择
    private ImageView windSpeedImg;//模式选择

    private boolean isPowerUp = false;
    private boolean isSimulator;
    private String deviceId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogControl.debug(TAG, "on create.");
        Bundle bundle = getArguments();
        isSimulator = (boolean) bundle.get("isSimulator");
        if (!isSimulator) {
            //在非体验的情况下显示这个
            deviceId = (String) bundle.get("deviceId");
        }

        View view = inflater.inflate(R.layout.device_ctr_air_cleaner, container, false);
        mainActivity = (MainActivity) getContext();


        backImg = (ImageView) view.findViewById(R.id.back);
        backImg.setOnClickListener(this);

        pmValueText = (TextView) view.findViewById(R.id.pm_value_text);

        sleepText = (TextView) view.findViewById(R.id.sleep_text);
        sleepText.setOnClickListener(this);

        anionText = (TextView) view.findViewById(R.id.anion_text);
        anionText.setOnClickListener(this);

        childLockText = (TextView) view.findViewById(R.id.child_lock_text);
        childLockText.setOnClickListener(this);

        smartModelText = (TextView) view.findViewById(R.id.smart_model_text);
        smartModelText.setOnClickListener(this);

        uvText = (TextView) view.findViewById(R.id.uv_text);
        uvText.setOnClickListener(this);

        powerCtrImg = (ImageView) view.findViewById(R.id.power_ctr_img);
        powerCtrImg.setOnClickListener(this);

        humidifyImg = (ImageView) view.findViewById(R.id.humidify_img);
        humidifyImg.setOnClickListener(this);

        modelImg = (ImageView) view.findViewById(R.id.model_img);
        modelImg.setOnClickListener(this);

        timerImg = (ImageView) view.findViewById(R.id.timer_img);
        timerImg.setOnClickListener(this);

        windSpeedImg = (ImageView) view.findViewById(R.id.wind_speed_img);
        windSpeedImg.setOnClickListener(this);

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
            case R.id.sleep_text: {
                if (isSleep) {
                    sleepText.setTextColor(Color.parseColor("#b2b2b2"));
                    isSleep = false;
                } else {
                    sleepText.setTextColor(Color.parseColor("#ffffff"));
                    isSleep = true;
                }
                break;
            }
            case R.id.anion_text: {
                if (isAnion) {
                    anionText.setTextColor(Color.parseColor("#b2b2b2"));
                    isAnion = false;
                } else {
                    anionText.setTextColor(Color.parseColor("#ffffff"));
                    isAnion = true;
                }
                break;
            }
            case R.id.child_lock_text: {
                if (isChildLock) {
                    childLockText.setTextColor(Color.parseColor("#b2b2b2"));
                    isChildLock = false;
                } else {
                    childLockText.setTextColor(Color.parseColor("#ffffff"));
                    isChildLock = true;
                }
                break;
            }
            case R.id.smart_model_text: {
                if (isSmartModel) {
                    smartModelText.setTextColor(Color.parseColor("#b2b2b2"));
                    isSmartModel = false;
                } else {
                    smartModelText.setTextColor(Color.parseColor("#ffffff"));
                    isSmartModel = true;
                }
                break;
            }
            case R.id.uv_text: {
                // TODO: 17/7/28 启用相关功能
                if (isUv) {
                    uvText.setTextColor(Color.parseColor("#b2b2b2"));
                    isUv = false;
                } else {
                    uvText.setTextColor(Color.parseColor("#ffffff"));
                    isUv = true;
                }
                break;
            }

            case R.id.humidify_img:
            case R.id.model_img:
            case R.id.timer_img:
            case R.id.wind_speed_img: {
                notSupportForSimulator();
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

    private void notSupportForSimulator() {
        Toast toast = Toast.makeText(mainActivity, "演示设备暂时不支持此功能！", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
