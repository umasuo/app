package com.umasuo.eva.ui.device.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.R;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.adapter.SelectDeviceAdapter;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.ui.device.DeviceItem;

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
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mainActivity = (MainActivity) this.getContext();
        View view = inflater.inflate(R.layout.device_add_selector, container, false);

        backBtn = (ImageView) view.findViewById(R.id.back);
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
        switch (view.getId()) {
            case R.id.back: {
                mainActivity.popBackStack();
                break;
            }
            case R.id.scan: {
                Toast.makeText(mainActivity, "此功能敬请期待.", Toast.LENGTH_SHORT).show();
            }
        }
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
        if (powerUpDevice == null) {
            powerUpDevice = new PowerUpDevice();
            powerUpDevice.setPreIndex(index);
            powerUpDevice.setIndex(mainActivity.getPagerSize());
            powerUpDevice.setIndex(mainActivity.addFragment(powerUpDevice));
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("model", (DeviceItem) item.get("model"));
        powerUpDevice.setArguments(bundle);

        mainActivity.showFragment(this, powerUpDevice, true);
    }

    /**
     * 系统预先定义20种设备, 另外再加一个其他选项.
     *
     * @return
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("开关", R.drawable.device_icon_kg));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("插座", R.drawable.device_icon_cz));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("照明", R.drawable.device_icon_d));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("防丢器", R.drawable.device_icon_fdq));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("窗帘", R.drawable.device_icon_cl));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("门锁", R.drawable.device_icon_ms));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("洗衣机", R.drawable.device_icon_xyj));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("空调", R.drawable.device_icon_kt));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("空调扇", R.drawable.device_icon_kts));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("风扇", R.drawable.device_icon_fs));
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("加湿器", R.drawable.device_icon_jsq));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("除湿机", R.drawable.device_icon_csj));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("净水器", R.drawable.device_icon_jsj));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("扫地机器人", R.drawable.device_icon_sdjqr));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("微波炉", R.drawable.device_icon_wbl));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("烤箱", R.drawable.device_icon_kx));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("电饭煲", R.drawable.device_icon_dfb));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("热水器", R.drawable.device_icon_rsq));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("取暖器", R.drawable.device_icon_qnj));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("空气净化器", R.drawable.device_icon_jhq));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("model", new DeviceItem("其他", R.drawable.device_icon_qt));
        list.add(map);

        return list;
    }


}