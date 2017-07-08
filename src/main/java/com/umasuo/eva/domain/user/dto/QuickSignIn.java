package com.umasuo.eva.domain.user.dto;

import java.io.Serializable;

public class QuickSignIn implements Serializable {

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
     * The validation code.
     */
    private String validationCode;

    public QuickSignIn() {
    }

    public QuickSignIn(String phone, String developerId, String validationCode) {
        this.phone = phone;
        this.developerId = developerId;
        this.validationCode = validationCode;
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

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}