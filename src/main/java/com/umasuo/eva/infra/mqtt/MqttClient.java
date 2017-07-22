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
    // static instance
    private static MqttClient client;

    //    private String host = "broker.evacloud.cn";
    private String host = "192.168.1.19";
    private int port = 1883;
    private String username;
    private String password;

    private MQTT mqtt;
    private BlockingConnection connection;

    public static MqttClient getInstance(String userId, String token) {
        if (client == null) {
            client = new MqttClient(userId, token);
        }
        return client;
    }

    public MqttClient(String userId, String token) {
        this.username = userId;
        this.password = token;
        try {
            mqtt = new MQTT();
            mqtt.setClientId(username);
            mqtt.setHost(host, port);
            mqtt.setUserName(username);
            mqtt.setPassword(password);
            mqtt.setKeepAlive((short) 600);
        } catch (Exception e) {
            LogControl.info("MQTT", "Connect to broker failed.");
        }
    }

    /**
     * 获取与broker的连接.
     *
     * @return
     */
    public BlockingConnection getConnect() throws Exception {
        //连接或者重新连接
        if (connection == null || !connection.isConnected()) {
            connection = mqtt.blockingConnection();
        }
        if (!connection.isConnected()) {
            connection.connect();
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

    public void startListen() {
        try {
            //todo 这个也应该放到独立的线程里面去
            MessageListener listener = new MessageListener(getConnect());
            listener.start();
        } catch (Exception e) {
            // TODO: 17/7/22 处理异常
            e.printStackTrace();
        }
    }
}
