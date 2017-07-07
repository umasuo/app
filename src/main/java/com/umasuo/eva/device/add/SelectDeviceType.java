package com.umasuo.eva.device.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.umasuo.eva.R;
import com.umasuo.eva.tools.adapter.SelectDeviceAdapter;
import com.umasuo.eva.tools.log.LogControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 添加设备第一步：选择设备类型.
 * 此fragment显示目前系统支持的设备类型，让用户进行选择，用户选择之后，进入到下一个页面.
 */
public class SelectDeviceType extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private String TAG = "SelectDeviceType";

    private ListView deviceList;
    private List<Map<String, Object>> data;
    private ImageView backBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
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
        Intent intent = new Intent();
        intent.setClassName(this.getContext(), "com.umasuo.eva.MainActivity");
        this.getContext().startActivity(intent);
        this.getActivity().overridePendingTransition(R.anim.choose_open, R.anim.choose_close);
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
        AddDeviceActivity activity = (AddDeviceActivity) this.getContext();
        // 设置选中了的设备
        activity.setSelectedDeviceName(item.get("title").toString());
        activity.replaceFragment(1);//切换到下一步
    }

    @Override
    public void onResume() {
        super.onResume();
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