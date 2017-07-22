package com.umasuo.eva.infra.mqtt;

/**
 * Created by umasuo on 17/7/22.
 */

public interface MqttMessageHandler {
    void handle(MqttMessage message);
}
