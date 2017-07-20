package com.umasuo.eva.infra.mqtt;

/**
 * Created by umasuo on 17/7/20.
 */
public class MqttConfig {

    private String host = "broker.evacloud.cn";

    private int port = 1883;

    private String username;

    private String password;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
