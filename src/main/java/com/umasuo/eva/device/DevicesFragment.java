package com.umasuo.eva.device;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.umasuo.eva.R;

/**
 * Created by liubin8095 on 2017/7/1.
 */

public class DevicesFragment extends Fragment implements View.OnClickListener{

    private ImageView devices_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        System.out.println("liubin 333 DevicesFragment onCreateView");
        View view = inflater.inflate(R.layout.devices_layout, container,false);

        devices_add = (ImageView)view.findViewById(R.id.devices_add);
        devices_add.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.devices_add:
                startChooseDevicesActivity();
                break;
        }
    }

    private void startChooseDevicesActivity(){
        Intent intent = new Intent();
        intent.setClassName(this.getContext(),"com.umasuo.eva.device.add.AddDeviceActivity");//打开一个activity
        this.getContext().startActivity(intent);
        this.getActivity().overridePendingTransition(R.anim.choose_open,R.anim.choose_close);
    }


}