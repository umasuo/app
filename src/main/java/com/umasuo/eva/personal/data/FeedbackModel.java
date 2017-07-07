package com.umasuo.eva.personal.data;

import com.umasuo.eva.tools.enums.FeedbackType;

import java.util.List;

/**
 * Created by umasuo on 17/7/7.
 */
public class FeedbackModel {

    /**
     * Uuid.
     */
    private String id;

    /**
     * The Created at.
     */
    private Long createdAt;

    /**
     * The Last modified at.
     */
    private Long lastModifiedAt;

    private long version;

    /**
     * 开发者ID.
     */
    private String developerId;

    /**
     * 设备ID.
     */
    private String deviceId;

    /**
     * 反馈种类：感谢类，疑问类，错误类，投诉类等
     */
    private FeedbackType type;

    private List<FeedbackContent> contents;

    private String title;

    private String phone;
}
