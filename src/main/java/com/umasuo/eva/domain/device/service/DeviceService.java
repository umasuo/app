package com.umasuo.eva.domain.device.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.umasuo.eva.domain.device.dto.DeviceModel;
import com.umasuo.eva.domain.device.dto.mapper.DeviceMapper;
import com.umasuo.eva.infra.api.device.DeviceCloudApi;
import com.umasuo.eva.infra.database.DatabaseHelper;
import com.umasuo.eva.infra.database.DeviceEntity;
import com.umasuo.eva.infra.log.LogControl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umasuo on 17/7/11.
 */

public class DeviceService {
    private static final String TAG = "DeviceService";

    private static DeviceService instance;

    /**
     * 服务器端的API.
     */
    private DeviceCloudApi deviceCloudApi;

    /**
     * 当前用户的设备列表.
     */
    private List<DeviceModel> devices;

    /**
     * 设备的配网token，每次启动APP都只有一个.
     */
    private String token;

    private DatabaseHelper dbHelper;

    //todo 这个是否需要共享一个呢？
    private SQLiteDatabase db;

    private Context context;

    /**
     * 保证同一个context下只有一个实例.
     *
     * @param context
     * @return
     */
    public static DeviceService getInstance(Context context) {
        if (instance == null || !context.equals(instance.getContext())) {
            instance = new DeviceService(context);
        }
        return instance;
    }

    /**
     * 初始化的时候读取数据库
     */
    public DeviceService(Context context) {
        // TODO: 17/7/11 这个需要优化
        dbHelper = DatabaseHelper.getInstance(context);
        db = dbHelper.getReadableDatabase();
        // 如果数据表不存在，则创建表
        db.execSQL(DeviceEntity.CREATE_TABLE_SQL);
        deviceCloudApi = new DeviceCloudApi();
        this.context = context;
    }

    /**
     * 获取该用户已经绑定的所有设备.
     *
     * @return
     */
    public List<DeviceModel> getAllDevice() {
        Cursor cursor = db.query(DeviceEntity.TABLE_NAME, DeviceEntity.projection, null, null, null, null, null);
        cursor.moveToFirst();
        devices = new ArrayList<>();

        if (cursor.getCount() > 0) {
            devices.add(DeviceMapper.toModel(cursor));
        }

        while (cursor.moveToNext()) {
            devices.add(DeviceMapper.toModel(cursor));
        }

        //todo 发送网络请求，获取最新的绑定列表.
        LogControl.debug(TAG, String.valueOf(devices.size()));

        // TODO: 17/7/26 假数据
        DeviceModel cz = new DeviceModel();
        cz.setName("插座");
        cz.setDeviceId("asdasfa-d1asdavz-zxcqwdas");
        cz.setProductTypeId("PowerStrip");

        DeviceModel kg = new DeviceModel();
        kg.setName("开关");
        kg.setDeviceId("asdasfa-d1asdavz-zxcqwdasdas");
        kg.setProductTypeId("switch");

        devices.add(cz);
        devices.add(kg);

        return devices;
    }

    public Context getContext() {
        return context;
    }

    /**
     * 获取用户当前所有的设备。
     *
     * @return
     */
    public List<DeviceModel> getDevices() {
        if (devices == null || devices.isEmpty()) {
            return getAllDevice();
        }

        return devices;
    }
}
