package com.umasuo.eva.tools.server.feedback.dto;

import java.io.Serializable;

public class ContentView implements Serializable {

    private static final long serialVersionUID = -2895830947304347194L;
    /**
     * create time for this content.
     */
    protected Long createdAt;

    /**
     * the real content.
     */
    private String contents;

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
