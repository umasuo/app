package com.umasuo.eva.ui.device;

import java.io.Serializable;

/**
 * Created by umasuo on 17/7/6.
 * 设备列表中的item.
 */
public class DeviceItem implements Serializable {

    private static final long serialVersionUID = -3294439470132569152L;

    private String mDeviceName;

    public int getmDeviceIconId() {
        return mDeviceIconId;
    }

    public void setmDeviceIconId(int mDeviceIconId) {
        this.mDeviceIconId = mDeviceIconId;
    }

    private int mDeviceIconId;

    public DeviceItem() {
    }

    public DeviceItem(String deviceName, int deviceIconId) {
        mDeviceName = deviceName;
        mDeviceIconId = deviceIconId;
    }


    @Override
    public String toString() {
        return "DeviceItem{" +
                "mDeviceName='" + mDeviceName + '\'' +
                ", mDeviceIconId=" + mDeviceIconId +
                '}';
    }

    public String getmDeviceName() {
        return mDeviceName;
    }

    public void setmDeviceName(String mDeviceName) {
        this.mDeviceName = mDeviceName;
    }
}
