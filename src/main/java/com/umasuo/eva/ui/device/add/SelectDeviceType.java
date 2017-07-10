package com.umasuo.eva.ui.device.add;

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
import com.umasuo.eva.infra.adapter.SelectDeviceAdapter;
import com.umasuo.eva.infra.log.LogControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 添加设备第一步：选择设备类型.
 * 此fragment显示目前系统支持的设备类型，让用户进行选择，用户选择之后，进入到下一个页面.
 */
public class SelectDeviceType extends FragmentRoot implements AdapterView.OnItemClickListener, View.OnClickListener {
    private String TAG = "SelectDeviceType";

    private ListView deviceList;
    private List<Map<String, Object>> data;
    private ImageView backBtn;

    private PowerUpDevice powerUpDevice;
    MainActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mActivity = (MainActivity) this.getContext();
        View view = inflater.inflate(R.layout.device_add_selector, container, false);

        backBtn = (ImageView) view.findViewById(R.id.device_select_back);
        backBtn.setOnClickListener(this);


        deviceList = view.findViewById(R.id.device_type_list);
        data = getData();
        SelectDeviceAdapter adapter = new SelectDeviceAdapter(getContext(), data);
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
//        Intent intent = new Intent();
//        intent.setClassName(this.getContext(), "com.umasuo.eva.MainActivity");
//        this.getContext().startActivity(intent);
//        this.getActivity().overridePendingTransition(R.anim.choose_open, R.anim.choose_close);
//        this.getActivity().finish();
        mActivity.getSupportFragmentManager().popBackStack();
    }

    /**
     * 设备列表中的一项被选中
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Map<String, Object> item = data.get(i);
        LogControl.debug(TAG, "click: " + i + ", name: " + item.get("title"));
        // 设置选中了的设备
        // TODO: 17/7/10 添加数据
        if (powerUpDevice == null) {
            powerUpDevice = new PowerUpDevice();
            powerUpDevice.setPreIndex(index);
        }
        powerUpDevice.setIndex(mActivity.addFragment(powerUpDevice));
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
        map.put("title", getString(R.string.device_cz));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.device_kg);
        map.put("title", getString(R.string.device_kg));
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.device_xy);
        map.put("title", getString(R.string.device_xy));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.device_bx);
        map.put("title", getString(R.string.device_bx));
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.device_qt);
        map.put("title", getString(R.string.device_qt));
        list.add(map);

        return list;
    }


}