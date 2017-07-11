package com.umasuo.eva.domain.device.dto;

import java.io.Serializable;

public class DeviceModel implements Serializable {

    /**
     * auto generated serial id.
     */
    private static final long serialVersionUID = 7285451484930726110L;


    private String id;

    /**
     * The Created at.
     */
    protected Long createdAt;

    /**
     * The Last modified at.
     */
    protected Long lastModifiedAt;

    /**
     * version used for update date check.
     */
    private Integer version;

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

}
