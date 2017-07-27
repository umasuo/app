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
 * 电源插排.
 */
public class PowerStripController extends FragmentRoot implements View.OnClickListener {
    private static final String TAG = "PowerStripController";

    private MainActivity mainActivity;

    //先写死四个试试, 后面通过配置文件和服务端数据来显示界面
    private ImageView backImg;
    private ImageView powerAll;
    boolean isAll = false;
    private ImageView power1;
    boolean isPower1 = false;
    private ImageView power2;
    boolean isPower2 = false;
    private ImageView usb1;
    boolean isUsb1 = false;
    private ImageView usb2;
    boolean isUsb2 = false;
    private ImageView powerCtr1;
    private ImageView powerCtr2;
    private ImageView usbCtr1;
    private ImageView usbCtr2;
    private ImageView timer;


    //是否只是体验中心
    private boolean isSimulator = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        LogControl.debug(TAG, "on create.");
        View view = inflater.inflate(R.layout.device_ctr_power_strip, container, false);
        mainActivity = (MainActivity) getContext();

        backImg = (ImageView) view.findViewById(R.id.back);
        backImg.setOnClickListener(this);

        powerAll = (ImageView) view.findViewById(R.id.power_all);
        power1 = (ImageView) view.findViewById(R.id.power_1);
        power2 = (ImageView) view.findViewById(R.id.power_2);
        usb1 = (ImageView) view.findViewById(R.id.usb_1);
        usb2 = (ImageView) view.findViewById(R.id.usb_2);
        powerCtr1 = (ImageView) view.findViewById(R.id.power_ctr_1);
        powerCtr2 = (ImageView) view.findViewById(R.id.power_ctr_2);
        usbCtr1 = (ImageView) view.findViewById(R.id.usb_ctr_1);
        usbCtr2 = (ImageView) view.findViewById(R.id.usb_ctr_2);
        timer = (ImageView) view.findViewById(R.id.timer);

        powerAll.setOnClickListener(this);
        power1.setOnClickListener(this);
        power2.setOnClickListener(this);
        usb1.setOnClickListener(this);
        usb2.setOnClickListener(this);
        powerCtr1.setOnClickListener(this);
        powerCtr2.setOnClickListener(this);
        usbCtr1.setOnClickListener(this);
        usbCtr2.setOnClickListener(this);
        timer.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                mainActivity.popBackStack();
                break;
            }
            case R.id.power_all: {
                powerAll();
                break;
            }
            case R.id.power_1:
            case R.id.power_ctr_1: {
                power1();
                break;
            }
            case R.id.power_2:
            case R.id.power_ctr_2: {
                power2();
                break;
            }
            case R.id.usb_1:
            case R.id.usb_ctr_1: {
                usb1();
                break;
            }
            case R.id.usb_2:
            case R.id.usb_ctr_2: {
                usb2();
                break;
            }
            case R.id.timer: {
                timer();
                break;
            }

        }
    }

    private void powerAll() {
        if (isAll) {
            isPower1 = true;
            isPower2 = true;
            isUsb1 = true;
            isUsb2 = true;
        } else {
            isPower1 = false;
            isPower2 = false;
            isUsb1 = false;
            isUsb2 = false;
        }
        power1();
        power2();
        usb1();
        usb2();
    }

    private void power1() {
        if (isPower1) {
            //现在为开启状态，关闭
            power1.setImageResource(R.drawable.device_ctr_img_pc_gray);
            powerCtr1.setImageResource(R.drawable.device_ctr_img_power_gray_bg);
            isPower1 = false;
        } else {
            power1.setImageResource(R.drawable.device_ctr_img_pc_blue);
            powerCtr1.setImageResource(R.drawable.device_ctr_img_power_blue_bg);
            isPower1 = true;
        }
        checkAll();
    }

    private void power2() {
        if (isPower2) {
            //现在为开启状态，关闭
            power2.setImageResource(R.drawable.device_ctr_img_pc_gray);
            powerCtr2.setImageResource(R.drawable.device_ctr_img_power_gray_bg);
            isPower2 = false;
        } else {
            power2.setImageResource(R.drawable.device_ctr_img_pc_blue);
            powerCtr2.setImageResource(R.drawable.device_ctr_img_power_blue_bg);
            isPower2 = true;
        }
        checkAll();
    }

    private void usb1() {
        if (isUsb1) {
            //现在为开启状态，关闭
            usb1.setImageResource(R.drawable.device_ctr_img_usb_gray);
            usbCtr1.setImageResource(R.drawable.device_ctr_img_power_gray_bg);
            isUsb1 = false;
        } else {
            usb1.setImageResource(R.drawable.device_ctr_img_usb_blue);
            usbCtr1.setImageResource(R.drawable.device_ctr_img_power_blue_bg);
            isUsb1 = true;
        }
        checkAll();
    }

    private void usb2() {
        if (isUsb2) {
            //现在为开启状态，关闭
            usb2.setImageResource(R.drawable.device_ctr_img_usb_gray);
            usbCtr2.setImageResource(R.drawable.device_ctr_img_power_gray_bg);
            isUsb2 = false;
        } else {
            usb2.setImageResource(R.drawable.device_ctr_img_usb_blue);
            usbCtr2.setImageResource(R.drawable.device_ctr_img_power_blue_bg);
            isUsb2 = true;
        }
        checkAll();
    }

    private void timer() {

    }

    /**
     * 检查是否需要将PowerAll 开启
     */
    private void checkAll() {
        if (isPower1 && isPower2 && isUsb1 && isUsb2) {
            powerAll.setImageResource(R.drawable.device_ctr_img_power_blue);
            isAll = true;
        } else {
            powerAll.setImageResource(R.drawable.device_ctr_img_power_gray);
            isAll = false;
        }
    }
}
