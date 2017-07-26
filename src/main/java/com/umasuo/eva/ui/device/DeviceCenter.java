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
import com.umasuo.eva.domain.device.dto.DeviceModel;
import com.umasuo.eva.domain.device.service.DeviceService;
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
    private ImageView deviceAdd;
    private ListView deviceItemList;
    private List<Map<String, Object>> data;
    private MainActivity activity;
    private SelectDeviceType selectDeviceType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        LogControl.debug(TAG, "on create.");
        View view = inflater.inflate(R.layout.devices, container, false);
        activity = (MainActivity) getContext();

        deviceAdd = (ImageView) view.findViewById(R.id.devices_add);
        deviceAdd.setOnClickListener(this);

        //从服务器或本地数据库拉取数据
        data = getData();

        deviceItemList = (ListView) view.findViewById(R.id.device_list);
        DeviceListAdapter adapter = new DeviceListAdapter(getContext(), data);
        deviceItemList.setAdapter(adapter);
        deviceItemList.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onShow() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.devices_add:
                addDevice();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //点击了某个设备
    }

    /**
     * 添加新设备。
     */
    private void addDevice() {
        if (selectDeviceType == null) {
            selectDeviceType = new SelectDeviceType();
            selectDeviceType.setPreIndex(index);
            selectDeviceType.setIndex(activity.getPagerSize());
        }
        activity.showFragment(this, selectDeviceType, true);
    }

    /**
     * 预先写死的数据，用于界面显示，后期可以部分写死，部分从服务器抓取.
     *
     * @return
     */
    private List<Map<String, Object>> getData() {
        //获取绑定的所有的设备
        List<DeviceModel> devices = DeviceService.getInstance(activity).getDevices();

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        // 根据产品类型，选择选定产品的icon－－后期这个icon应该放到服务器动态设定
        for (int i = 0; i < devices.size(); i++) {
            DeviceModel curDevice = devices.get(i);
            switch (curDevice.getProductTypeId()) {
                case "switch": {
                    //智能开关
                    map = new HashMap<String, Object>();
                    map.put("icon", R.drawable.device_img_kg);
                    map.put("name", curDevice.getName());
                    map.put("id", curDevice.getDeviceId());
                    list.add(map);
                    break;
                }
                case "PowerStrip": {
                    //电源插座
                    map = new HashMap<String, Object>();
                    map.put("icon", R.drawable.device_icon_cz);
                    map.put("name", curDevice.getName());
                    map.put("id", curDevice.getDeviceId());
                    list.add(map);
                    break;
                }
                default: {
                    map = new HashMap<String, Object>();
                    map.put("icon", R.drawable.device_icon_qt);
                    map.put("name", getString(R.string.device_qt));
                    list.add(map);
                }
            }
        }

        return list;
    }

}