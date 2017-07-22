package com.umasuo.eva.infra.mqtt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.umasuo.eva.MainActivity;
import com.umasuo.eva.domain.device.DeviceMessageHandler;
import com.umasuo.eva.domain.user.UserMessageHandler;
import com.umasuo.eva.infra.log.LogControl;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

/**
 * 订阅MQTT的消息，并将其转发到具体的业务逻辑里面去.
 */
public class MessageListener extends Thread {

    private BlockingConnection connection;
    private Gson gson;

    public MessageListener(BlockingConnection connection) {
        this.connection = connection;
        gson = new GsonBuilder().create();
    }

    /**
     * 监听MQTT的消息，并即使反馈.
     */
    @Override
    public void run() {
        LogControl.debug("Listener", " Start listen.");
        try {
            Topic[] topics = {new Topic("user:user1", QoS.AT_LEAST_ONCE)};
            connection.subscribe(topics);
            while (true) {
                Message message = connection.receive();
                if (message != null && message.getPayload() != null) {
                    byte[] payload = message.getPayload();

                    MqttMessage msg = gson.fromJson(new String(payload), MqttMessage.class);

                    switch (msg.getType()) {
                        case 1:
                            // 功能
                            DeviceMessageHandler.getInstance(MainActivity.getInstance()).handle(msg);
                            break;
                        case 2:
                            // 数据
                            UserMessageHandler.getInstance(MainActivity.getInstance()).handle(msg);
                            break;
                    }
                    message.ack();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}