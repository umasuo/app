package com.umasuo.eva.domain.device.dto;

/**
 * Created by umasuo on 17/7/26.
 */

public class DeviceInitModel {
    private String userId;
    private String apiUrl = "http://user.evacloud.cn";
    private String brokerUrl = "broker.evacloud.cn";

    public DeviceInitModel(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"userId\":\"" + userId + "\"" +
                ", \"apiUrl\":\"" + apiUrl + "\"" +
                ", \"brokerUrl:\"" + brokerUrl + "\"" +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }
}
