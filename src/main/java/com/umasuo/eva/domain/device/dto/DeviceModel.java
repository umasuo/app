package com.umasuo.eva.domain.device.dto;

import java.io.Serializable;

public class DeviceModel implements Serializable {

    /**
     * auto generated serial id.
     */
    private static final long serialVersionUID = 7285451484930726110L;

    /**
     * 设备ID。
     */
    private String deviceId;

    /**
     * 从系统批量获取的ID，用于唯一标志该设备。
     */
    private String unionId;

    /**
     * 产品ID。
     */
    private String productId;

    /**
     * 产品类型ID。
     */
    private String productTypeId;

    /**
     * 开发者ID，任何接入云平台的设备，都属于一个固定的开发者.
     */
    private String developerId;

    private String name;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeviceModel{" +
                "deviceId='" + deviceId + '\'' +
                ", unionId='" + unionId + '\'' +
                ", productId='" + productId + '\'' +
                ", productTypeId='" + productTypeId + '\'' +
                ", developerId='" + developerId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
