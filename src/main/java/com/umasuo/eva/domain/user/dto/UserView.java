package com.umasuo.eva.domain.user.dto;

import java.io.Serializable;

public class UserView implements Serializable {


    private static final long serialVersionUID = -3792465250729122435L;
    /**
     * UserId of the developer user.
     */
    private String userId;

    /**
     * Developer id.
     */
    private String developerId;

    /**
     * Which device this user from.
     */
    private String deviceDefinitionId;

    /**
     * User's email. unique on this platform.
     */
    private String email;

    /**
     * User's mobile phone. unique on this platform.
     */
    private String phone;

    /**
     * User's externalId.
     */
    private String externalId;

    /**
     * User's name.
     */
    private String name;

    /**
     * User's icon.
     */
    private String icon;

    /**
     * User's age.
     */
    private int age;

    /**
     * User's signature.
     */
    private String signature;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getDeviceDefinitionId() {
        return deviceDefinitionId;
    }

    public void setDeviceDefinitionId(String deviceDefinitionId) {
        this.deviceDefinitionId = deviceDefinitionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "UserView{" +
                "userId='" + userId + '\'' +
                ", developerId='" + developerId + '\'' +
                ", deviceDefinitionId='" + deviceDefinitionId + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", externalId='" + externalId + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", age=" + age +
                ", signature='" + signature + '\'' +
                '}';
    }
}
