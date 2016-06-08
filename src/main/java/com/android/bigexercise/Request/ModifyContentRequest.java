package com.android.bigexercise.Request;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class ModifyContentRequest {
    private Long userId;
    private Long contentId;
    private String title;
    private String content;

    public ModifyContentRequest() {
    }

    public ModifyContentRequest(Long userId, Long contentId, String title, String content) {
        this.userId = userId;
        this.contentId = contentId;
        this.title = title;
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
