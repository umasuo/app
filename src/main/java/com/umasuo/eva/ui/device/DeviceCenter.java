package com.umasuo.eva.ui.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.adapter.DeviceListAdapter;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.ui.device.add.SelectDeviceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liubin8095 on 2017/7/1.
 * 我的设备界面，用于显示用户已经添加的设备，以及提供一个设备控制的入口.
 */
public class DeviceCenter extends FragmentRoot implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "DeviceCenter";
    private ImageView devices_add;
    private ListView deviceItemList;
    private List<Map<String, Object>> data;
    MainActivity activity;
    private SelectDeviceType selectDeviceType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        LogControl.debug(TAG, "on create.");
        View view = inflater.inflate(R.layout.devices, container, false);

        devices_add = (ImageView) view.findViewById(R.id.devices_add);
        devices_add.setOnClickListener(this);

        //从服务器或本地数据库拉取数据
        data = getData();

        deviceItemList = (ListView) view.findViewById(R.id.device_list);
        DeviceListAdapter adapter = new DeviceListAdapter(getContext(), data);
        deviceItemList.setAdapter(adapter);
        deviceItemList.setOnItemClickListener(this);
        activity = (MainActivity) getContext();

        // TODO: 17/7/11 读取数据库并发起网络请求去拉取最新的设备列表
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        LogControl.debug(TAG, "onHiddenChanged hidden = " + hidden);
//        super.onHiddenChanged(hidden);
//        if (hidden) {
//            activity.hideBottom();
//        } else {
//            activity.showBottom();
//        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        LogControl.debug(TAG, "click item: " + i);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.devices_add:
                startSelectDevice();
                break;
        }
    }

    private void startSelectDevice() {

        if (selectDeviceType == null) {
            selectDeviceType = new SelectDeviceType();
            selectDeviceType.setPreIndex(index);
            selectDeviceType.setIndex(activity.getPagerSize());
//            selectDeviceType.setIndex(activity.addFragment(selectDeviceType));
        }
//        activity.showPage(selectDeviceType.getIndex());
        activity.showFragment(this,selectDeviceType,true);

    }

    /**
     * 预先写死的数据，用于界面显示，后期可以部分写死，部分从服务器抓取.
     *
     * @return
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.device_cz);
        map.put("name", getString(R.string.device_cz));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.device_kg);
        map.put("name", getString(R.string.device_kg));
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.device_xy);
        map.put("name", getString(R.string.device_xy));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.device_bx);
        map.put("name", getString(R.string.device_bx));
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.device_qt);
        map.put("name", getString(R.string.device_qt));
        list.add(map);

        return list;
    }

}