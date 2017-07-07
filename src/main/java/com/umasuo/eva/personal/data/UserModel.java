package com.umasuo.eva.personal.data;

/**
 * Created by umasuo on 17/7/7.
 */
public class UserModel {


    /**
     * UserId of the developer user.
     */
    private String userId;

    /**
     * 用户属于哪个开发者，用户如果属于平台，那么其developerID则为空.
     */
    private String developerId;

    /**
     * User's email. unique on this platform.
     */
    private String email;

    /**
     * User's mobile phone. unique on this platform.
     */
    private String phone;

    /**
     * User's name.
     */
    private String name;

    /**
     * User's icon  头像.
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
}
