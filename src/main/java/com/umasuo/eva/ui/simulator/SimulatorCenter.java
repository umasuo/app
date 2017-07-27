package com.umasuo.eva.ui.simulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.ui.device.contoller.BulbController;
import com.umasuo.eva.ui.device.contoller.PowerStripController;
import com.umasuo.eva.ui.device.contoller.SwitchController;

/**
 * 体验中心界面，用于操作虚拟设备.
 */
public class SimulatorCenter extends FragmentRoot implements View.OnClickListener {

    private static final String TAG = "PersonalCenter";
    private MainActivity mainActivity;

    private ImageView powerStrip;
    private PowerStripController powerStripController;

    private ImageView switchImg;
    private SwitchController switchController;

    private ImageView bulbImg;
    private BulbController bulbController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.simulator, container, false);
        mainActivity = (MainActivity) getContext();

        powerStrip = (ImageView) view.findViewById(R.id.power_strip_try_img);
        powerStrip.setOnClickListener(this);

        switchImg = (ImageView) view.findViewById(R.id.switch_try_img);
        switchImg.setOnClickListener(this);

        bulbImg = (ImageView) view.findViewById(R.id.bulb_try_img);
        bulbImg.setOnClickListener(this);
        //init data
        return view;
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.power_strip_try_img: {
                if (powerStripController == null) {
                    powerStripController = new PowerStripController();
                    powerStripController.setIndex(mainActivity.getPagerSize());
                    powerStripController.setPreIndex(index);
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("isSimulator", true);
                powerStripController.setArguments(bundle);
                mainActivity.showFragment(this, powerStripController, true);
                break;
            }
            case R.id.switch_try_img: {
                if (switchController == null) {
                    switchController = new SwitchController();
                    switchController.setIndex(mainActivity.getPagerSize());
                    switchController.setPreIndex(index);
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("isSimulator", true);
                switchController.setArguments(bundle);
                mainActivity.showFragment(this, switchController, true);
                break;
            }
            case R.id.bulb_try_img: {
                if (bulbController == null) {
                    bulbController = new BulbController();
                    bulbController.setIndex(mainActivity.getPagerSize());
                    bulbController.setPreIndex(index);
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("isSimulator", true);
                bulbController.setArguments(bundle);
                mainActivity.showFragment(this, bulbController, true);
                break;
            }
        }
    }
}