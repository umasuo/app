package com.umasuo.eva.domain.user.dto;

import java.io.Serializable;

/**
 * Created by umasuo on 17/7/21.
 * sign in with phone and password
 */
public class SignIn implements Serializable {

    private static final long serialVersionUID = -713469888306165568L;
    /**
     * user's mobile phone. unique on this platform.
     */
    private String phone;

    /**
     * developer id.
     */
    private String developerId;

    /**
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     */
    private String password;

    public SignIn(String phone, String developerId, String password) {
        this.phone = phone;
        this.developerId = developerId;
        this.password = password;
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

    @Override
    public String toString() {
        return "SignIn{" +
                "phone='" + phone + '\'' +
                ", developerId='" + developerId + '\'' +
                '}';
    }
}
