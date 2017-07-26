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
import com.umasuo.eva.domain.device.dto.ProductTypeModel;
import com.umasuo.eva.infra.FragmentRoot;
import com.umasuo.eva.infra.adapter.SelectDeviceAdapter;
import com.umasuo.eva.infra.log.LogControl;

import java.util.ArrayList;
import java.util.List;


/**
 * 添加设备第一步：选择设备类型.
 * 此fragment显示目前系统支持的设备类型，让用户进行选择，用户选择之后，进入到下一个页面.
 */
public class SelectDeviceType extends FragmentRoot implements AdapterView.OnItemClickListener, View.OnClickListener {
    private String TAG = "SelectDeviceType";

    private ListView deviceList;
    private List<ProductTypeModel> data;
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
        ProductTypeModel productType = data.get(i);
        LogControl.debug(TAG, "click: " + i + ", name: " + productType.name);
        // 设置选中了的设备
        if (powerUpDevice == null) {
            powerUpDevice = new PowerUpDevice();
            powerUpDevice.setPreIndex(index);
            powerUpDevice.setIndex(mainActivity.getPagerSize());
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("productType", productType);
        powerUpDevice.setArguments(bundle);

        mainActivity.showFragment(this, powerUpDevice, true);
    }

    /**
     * 系统预先定义20种设备, 另外再加一个其他选项.
     *
     * @return
     */
    private List<ProductTypeModel> getData() {
        List<ProductTypeModel> list = new ArrayList<ProductTypeModel>();

        list.add(new ProductTypeModel(ProductTypeModel.SWITCH, R.drawable.device_icon_kg, "开关"));

        list.add(new ProductTypeModel(ProductTypeModel.POWER_STRIP, R.drawable.device_icon_cz, "插座"));

        list.add(new ProductTypeModel(ProductTypeModel.BULB_WHITE, R.drawable.device_icon_d, "照明"));

        list.add(new ProductTypeModel(ProductTypeModel.GPRS, R.drawable.device_icon_fdq, "防丢器"));

        list.add(new ProductTypeModel(ProductTypeModel.CURTAIN, R.drawable.device_icon_cl, "窗帘"));

        list.add(new ProductTypeModel(ProductTypeModel.LOCK, R.drawable.device_icon_ms, "门锁"));

        list.add(new ProductTypeModel(ProductTypeModel.WASHER, R.drawable.device_icon_xyj, "洗衣机"));

        list.add(new ProductTypeModel(ProductTypeModel.AIR_CONDITION, R.drawable.device_icon_kt, "空调"));

        list.add(new ProductTypeModel(ProductTypeModel.AIR_COOLER, R.drawable.device_icon_kts, "空调扇"));

        list.add(new ProductTypeModel(ProductTypeModel.AIR_FAN, R.drawable.device_icon_fs, "风扇"));

        list.add(new ProductTypeModel(ProductTypeModel.HUMIDIFIER, R.drawable.device_icon_jsq, "加湿器"));

        list.add(new ProductTypeModel(ProductTypeModel.DEHUMIDIFIER, R.drawable.device_icon_csj, "除湿机"));

        list.add(new ProductTypeModel(ProductTypeModel.WATER_CLEANER, R.drawable.device_icon_jsj, "净水器"));

        list.add(new ProductTypeModel(ProductTypeModel.CLEAN_ROBOT, R.drawable.device_icon_sdjqr, "扫地机器人"));

        list.add(new ProductTypeModel(ProductTypeModel.MICROWAVE_OVEN, R.drawable.device_icon_wbl, "微波炉"));

        list.add(new ProductTypeModel(ProductTypeModel.OVEN, R.drawable.device_icon_kx, "烤箱"));

        list.add(new ProductTypeModel(ProductTypeModel.COOKER, R.drawable.device_icon_dfb, "电饭煲"));

        list.add(new ProductTypeModel(ProductTypeModel.WATER_HEATER, R.drawable.device_icon_rsq, "热水器"));

        list.add(new ProductTypeModel(ProductTypeModel.HEATER, R.drawable.device_icon_qnj, "取暖器"));

        list.add(new ProductTypeModel(ProductTypeModel.AIR_CLEANER, R.drawable.device_icon_jhq, "空气净化器"));

        list.add(new ProductTypeModel(ProductTypeModel.OTHER, R.drawable.device_icon_qt, "其他"));

        return list;
    }


}