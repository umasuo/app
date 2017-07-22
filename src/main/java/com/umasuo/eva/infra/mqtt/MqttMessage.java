package com.umasuo.eva.infra.mqtt;

import java.io.Serializable;

/**
 * Created by umasuo on 17/7/22.
 * the basic message structure of message in mqtt.
 */
public class MqttMessage implements Serializable {
    private static final long serialVersionUID = -252817418165531917L;

    private int type;
    private long t;
    private String deviceId;
    private Payload payload;

    public static class Payload implements Serializable {
        private static final long serialVersionUID = 4201206780152347634L;
        private String id;
        private String data;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"id\":\"" + id + "\"" +
                    ", \"data\":\"" + data + "\"" +
                    '}';
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    // TODO: 17/7/22 这个需要替换成工具才好，不过这样可以减少APK包的大小
    @Override
    public String toString() {
        return "{" +
                "\"type\":\"" + type +
                "\", \"t\":" + t +
                ", \"deviceId\":\"" + deviceId + "\"" +
                ", \"payload\":" + payload +
                '}';
    }
}
