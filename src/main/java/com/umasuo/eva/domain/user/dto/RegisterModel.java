package com.umasuo.eva.domain.user.dto;

import java.io.Serializable;

public class RegisterModel implements Serializable {

    private static final long serialVersionUID = -5286102719342030974L;
    /**
     * user's mobile phone. unique on this platform.
     */
    private String phone;

    /**
     * developer id.
     */
    private String developerId;

    /**
     * password.
     */
    private String password;

    /**
     * sms code.
     */
    private String smsCode;

    public RegisterModel() {
    }

    public RegisterModel(String phone, String developerId, String password, String smsCode) {
        this.phone = phone;
        this.developerId = developerId;
        this.password = password;
        this.smsCode = smsCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "phone='" + phone + '\'' +
                ", developerId='" + developerId + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }
}