package com.umasuo.eva.domain.feedback.dto;

import com.umasuo.eva.infra.enums.FeedbackType;

import java.io.Serializable;
import java.util.List;

public class FeedbackView implements Serializable{

    private static final long serialVersionUID = 8869700228132652929L;
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
     * 用户ID.
     */
    private String userId;

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

    private List<ContentView> contents;

    private String title;

    private String phone;

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Long lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public FeedbackType getType() {
        return type;
    }

    public void setType(FeedbackType type) {
        this.type = type;
    }

    public List<ContentView> getContents() {
        return contents;
    }

    public void setContents(List<ContentView> contents) {
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
