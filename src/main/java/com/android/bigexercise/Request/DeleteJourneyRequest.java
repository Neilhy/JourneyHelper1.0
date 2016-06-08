package com.android.bigexercise.Request;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class DeleteJourneyRequest {
    private Long userId;
    private Long contentId;

    public DeleteJourneyRequest() {
    }

    public DeleteJourneyRequest(Long userId, Long contentId) {
        this.userId = userId;
        this.contentId = contentId;
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
}
