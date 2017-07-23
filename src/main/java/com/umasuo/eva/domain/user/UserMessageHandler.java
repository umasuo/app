package com.umasuo.eva.domain.user;

import android.content.Context;

import com.umasuo.eva.infra.log.LogControl;
import com.umasuo.eva.infra.mqtt.MqttMessage;
import com.umasuo.eva.infra.mqtt.MqttMessageHandler;

/**
 * Created by umasuo on 17/7/22.
 * 用来处理云平台发送给App的消息.
 */
public class UserMessageHandler implements MqttMessageHandler {
    private static final String TAG = "DeviceMessageHandler >> ";
    private static UserMessageHandler instance;

    private Context context;

    public static UserMessageHandler getInstance() {
        return instance;
    }

    public static UserMessageHandler getInstance(Context context) {
        if (instance == null || !instance.context.equals(context)) {
            instance = new UserMessageHandler(context);
        }
        return instance;
    }

    public UserMessageHandler(Context context) {
        this.context = context;
    }

    /**
     * 处理消息.
     *
     * @param message
     */
    @Override
    public void handle(MqttMessage message) {
        LogControl.debug(TAG, "hand user's message: " + message.toString());
        // TODO: 17/7/22 处理数据型消息
    }
}
