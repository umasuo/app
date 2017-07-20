package com.umasuo.eva.infra.mqtt;

import com.umasuo.eva.infra.log.LogControl;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

/**
 * Created by umasuo on 17/7/20.
 * mqtt client, used to receive and send message to device.
 */
public class MqttClient {

    private MqttConfig config;

    private MQTT mqtt;

    BlockingConnection connection;

    public MqttClient(String userId, String token) {
        this.config = new MqttConfig();
        config.setPassword(token);
        config.setUsername(userId);
        try {
            mqtt = new MQTT();
            mqtt.setHost(config.getHost(), config.getPort());
            mqtt.setUserName(config.getUsername());
            mqtt.setPassword(config.getPassword());
        } catch (Exception e) {
            LogControl.info("MQTT", "Connect to broker failed.");
        }
    }

    public MqttClient(MqttConfig config) {
        this.config = config;
        try {
            mqtt = new MQTT();
            mqtt.setHost(config.getHost(), config.getPort());
            mqtt.setUserName(config.getUsername());
            mqtt.setPassword(config.getPassword());
        } catch (Exception e) {
            LogControl.info("MQTT", "Connect to broker failed.");
        }
    }

    /**
     * 获取与broker的连接.
     *
     * @return
     */
    public BlockingConnection getConnect() {
        //连接或者重新连接
        if (connection == null || !connection.isConnected()) {
            connection = mqtt.blockingConnection();
        }
        return connection;
    }

    /**
     * 下发消息到某个topic.
     *
     * @param topic
     * @param payload
     * @return
     */
    public boolean publish(final String topic, final byte[] payload) {
        try {
            getConnect().publish(topic, payload, QoS.AT_LEAST_ONCE, false);
            return true;
        } catch (Exception e) {
            LogControl.info("MQTT", "Publish message failed.");
            // TODO: 17/7/20 retry??
            return false;
        }
    }
}
