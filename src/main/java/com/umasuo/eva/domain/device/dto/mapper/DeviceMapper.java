package com.umasuo.eva.domain.device.dto.mapper;

import android.database.Cursor;

import com.umasuo.eva.domain.device.dto.DeviceModel;
import com.umasuo.eva.infra.database.DeviceEntity;

/**
 * Created by umasuo on 17/7/24.
 */
public class DeviceMapper {

    public static DeviceModel toModel(Cursor cursor) {
        DeviceModel device = null;
        if (cursor.getCount() > 0) {
            device = new DeviceModel();
            device.setDeviceId(cursor.getString(cursor.getColumnIndexOrThrow(DeviceEntity.DEVICE_ID)));
            device.setDeveloperId(cursor.getString(cursor.getColumnIndexOrThrow(DeviceEntity.DEVELOPER_ID)));
            device.setProductId(cursor.getString(cursor.getColumnIndexOrThrow(DeviceEntity.PRODUCT_ID)));
            device.setName(cursor.getString(cursor.getColumnIndexOrThrow(DeviceEntity.NAME)));
            device.setUnionId(cursor.getString(cursor.getColumnIndexOrThrow(DeviceEntity.UNION_ID)));
        }
        return device;
    }
}
