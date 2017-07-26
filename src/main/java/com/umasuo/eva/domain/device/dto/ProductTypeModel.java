package com.umasuo.eva.domain.device.dto;

import java.io.Serializable;

public class ProductTypeModel implements Serializable {

    /**
     * auto generated serial id.
     */
    private static final long serialVersionUID = 7285451484930726110L;

    //开关
    public static final String AIR_CLEANER = "air_cleaner";
    public static final String AIR_CONDITION = "air_condition";
    public static final String AIR_COOLER = "air_cooler";
    public static final String AIR_FAN = "air_fan";
    public static final String BULB_WHITE = "bulb_white";
    public static final String BULB_COLOUR = "bulb_colour";
    public static final String CLEAN_ROBOT = "clean_robot";
    public static final String COOKER = "cooker";
    public static final String CURTAIN = "curtain";
    public static final String DEHUMIDIFIER = "dehumidifier";
    public static final String GPRS = "gprs";
    public static final String HEATER = "heater";
    public static final String HUMIDIFIER = "humidifier";
    public static final String LOCK = "lock";
    public static final String MICROWAVE_OVEN = "microwave_oven";
    public static final String OVEN = "oven";
    public static final String POWER_STRIP = "power_strip";
    public static final String SWITCH = "switch";
    public static final String WASHER = "washer";
    public static final String WATER_CLEANER = "water_cleaner";
    public static final String WATER_HEATER = "water_heater";
    public static final String OTHER = "other";

    /**
     * 设备类型ID.
     */
    public String id;

    public int iconId;

    public String name;

    public ProductTypeModel(String id, int iconId, String name) {
        this.id = id;
        this.iconId = iconId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductTypeModel{" +
                "id='" + id + '\'' +
                ", iconId=" + iconId +
                ", name='" + name + '\'' +
                '}';
    }
}
