package com.umasuo.eva.domain.device.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.umasuo.eva.domain.device.dto.DeviceModel;
import com.umasuo.eva.infra.database.DatabaseHelper;
import com.umasuo.eva.infra.database.DeviceEntity;
import com.umasuo.eva.infra.api.device.DeviceCloudApi;

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
    private static List<DeviceModel> devices;

    /**
     * 设备的配网token，每次启动APP都只有一个.
     */
    private String token;

    //todo  这个是否需要共享一个呢？
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
        if (instance == null) {
            instance = new DeviceService(context);
        } else if (!context.equals(instance.getContext())) {
            //切换了context，需要重新加载, // TODO: 17/7/10 待优化
            instance = new DeviceService(context);
        }
        return instance;
    }

    public void getToken() {

    }

    /**
     * 初始化的时候读取数据库
     */
    public DeviceService(Context context) {
        // TODO: 17/7/11 这个需要优化
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getReadableDatabase();
        // 如果数据表不存在，则创建表
        db.execSQL(DeviceEntity.CREATE_TABLE_SQL);
        deviceCloudApi = new DeviceCloudApi();
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
