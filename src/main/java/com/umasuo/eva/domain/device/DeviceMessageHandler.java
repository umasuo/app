package com.umasuo.eva.domain.device;

import android.content.Context;
import android.widget.Toast;

import com.umasuo.eva.MainActivity;
import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.infra.mqtt.MqttMessage;
import com.umasuo.eva.infra.mqtt.MqttMessageHandler;

/**
 * Created by umasuo on 17/7/22.
 * 用来处理设备向App发送的消息，功能调用、或者是状态同步之类的。
 */
public class DeviceMessageHandler implements MqttMessageHandler {
    private static final String TAG = "DeviceMessageHandler >> ";
    private static DeviceMessageHandler instance;

    private Context context;

    public static DeviceMessageHandler getInstance() {
        return instance;
    }

    public static DeviceMessageHandler getInstance(Context context) {
        if (instance == null || !instance.context.equals(context)) {
            instance = new DeviceMessageHandler(context);
        }
        return instance;
    }

    public DeviceMessageHandler(Context context) {
        this.context = context;
    }

    /**
     * 处理硬件发送的消息.
     *
     * @param message
     */
    @Override
    public void handle(MqttMessage message) {
        LogControl.debug(TAG, "hand device's message: " + message.toString());
        // TODO: 17/7/22 处理功能型消息
    }
}
