package com.umasuo.eva.device.data;

/**
 * Created by umasuo on 17/7/7.
 * 用户的设备类，用来存储用户的设备数据.
 * todo 可能不同类型的设备需要使用不同的model
 */
public class DeviceModel {

    /**
     * 设备ID，每个设备都会有一个唯一ID.
     */
    private String deviceId;

    /**
     * 设备绑定时间.
     */
    private Long createdAt;

    /**
     * version, 用于修改设备信息用.
     */
    private Integer version;

    /**
     * 用户给设备起的名字，例如"书房开关".
     */
    private String name;

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
}
