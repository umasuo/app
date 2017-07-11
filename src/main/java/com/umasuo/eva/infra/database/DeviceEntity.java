package com.umasuo.eva.infra.database;

/**
 * Created by umasuo on 17/7/11.
 * 设备相关的表
 */
public class DeviceEntity {

    public static final String TABLE_NAME = "device";

    public static final String DEVICE_ID = "device_id";
    public static final String OWNER_ID = "owner_id";
    public static final String DEVELOPER_ID = "developer_id";
    public static final String DEVICE_TYPE = "device_type";
    public static final String NAME = "name";

    // table creator sql
    public static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    DEVICE_ID + " TEXT PRIMARY KEY," +
                    OWNER_ID + " TEXT," +
                    DEVELOPER_ID + " TEXT," +
                    DEVICE_TYPE + " TEXT," +
                    NAME + " TEXT )";

    public static final String DELETE_TABLE_SQL =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String[] projection = {
            DEVICE_ID,
            DEVELOPER_ID,
            NAME,
            DEVICE_TYPE
    };

}
