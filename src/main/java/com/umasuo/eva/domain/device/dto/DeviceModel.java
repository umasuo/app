package com.umasuo.eva.domain.device.dto;

import java.io.Serializable;

public class DeviceModel implements Serializable {

    /**
     * auto generated serial id.
     */
    private static final long serialVersionUID = 7285451484930726110L;


    private String id;

    /**
     * 从系统批量获取的ID，用于唯一标志该设备。
     */
    private String unionId;

    /**
     * 任何接入云平台的设备，都属于一个事先定义好的设备类型.
     */
    private String productId;

    /**
     * 开发者ID，任何接入云平台的设备，都属于一个固定的开发者.
     */
    private String developerId;

    private String deviceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "DeviceModel{" +
                "id='" + id + '\'' +
                ", unionId='" + unionId + '\'' +
                ", productId='" + productId + '\'' +
                ", developerId='" + developerId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
