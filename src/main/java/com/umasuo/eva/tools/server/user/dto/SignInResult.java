package com.umasuo.eva.tools.server.user.dto;

import java.io.Serializable;

public class SignInResult implements Serializable {

    private static final long serialVersionUID = 571055013624272488L;

    /**
     * Customer for view.
     */
    private UserView userView;

    /**
     * String token.
     */
    private String token;

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SignInResult{" +
                "userView=" + userView +
                ", token='" + token + '\'' +
                '}';
    }
}