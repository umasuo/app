package com.umasuo.eva.device.add;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umasuo.eva.R;
import com.umasuo.eva.tools.log.LogControl;

/**
 * Created by liubin8095 on 2017/7/2.
 * 添加设备第二步：提醒用户确认设备已经开启，相关指示灯已经亮起.
 */
public class PowerUpDevice extends Fragment implements View.OnClickListener {

    private String TAG = "PowerUpDevice";
    private ImageView backBtn;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Fresco.initialize(this.getContext());//放在加载布局之前


        View view = inflater.inflate(R.layout.device_add_power_up, container, false);
        backBtn = (ImageView) view.findViewById(R.id.device_power_up_back);
        backBtn.setOnClickListener(this);
        textView = (TextView) view.findViewById(R.id.device_power_up_content);

        return view;
    }

    @Override
    public void onClick(View view) {
        ((AddDeviceActivity) this.getContext()).replaceFragment(0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogControl.debug(TAG, "attach");
    }

    @Override
    public void onResume() {
        super.onResume();
        AddDeviceActivity activity = (AddDeviceActivity) getContext();
        textView.setText(textView.getText() + " : " + activity.getSelectedDeviceName());
        LogControl.debug(TAG, "resume: " + activity.getSelectedDeviceName());

    }

    @Override
    public void onPause() {
        super.onPause();
        AddDeviceActivity activity = (AddDeviceActivity) getContext();
        textView.setText(textView.getText() + " : " + activity.getSelectedDeviceName());
        LogControl.debug(TAG, "pause: " + activity.getSelectedDeviceName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AddDeviceActivity activity = (AddDeviceActivity) getContext();
        textView.setText(textView.getText() + " : " + activity.getSelectedDeviceName());
        LogControl.debug(TAG, "destroy: " + activity.getSelectedDeviceName());
    }
}