package com.umasuo.eva.ui.device;

import java.io.Serializable;

/**
 * Created by umasuo on 17/7/6.
 * 设备列表中的item.
 */
public class DeviceItem implements Serializable {

    private static final long serialVersionUID = -3294439470132569152L;

    private String deviceName;

    private int deviceIconId;

    public DeviceItem() {
    }

    public DeviceItem(String deviceName, int deviceIconId) {
        this.deviceName = deviceName;
        this.deviceIconId = deviceIconId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getDeviceIconId() {
        return deviceIconId;
    }

    public void setDeviceIconId(int deviceIconId) {
        this.deviceIconId = deviceIconId;
    }

    @Override
    public String toString() {
        return "DeviceItem{" +
                "deviceName='" + deviceName + '\'' +
                ", deviceIconId=" + deviceIconId +
                '}';
    }
}
