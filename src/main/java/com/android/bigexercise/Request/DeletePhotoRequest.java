package com.android.bigexercise.Request;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class DeletePhotoRequest {
    private Long userId;
    private Long contentId;
    private String photoUrl;

    public DeletePhotoRequest() {
    }

    public DeletePhotoRequest(Long userId, Long contentId, String photoUrl) {
        this.userId = userId;
        this.contentId = contentId;
        this.photoUrl = photoUrl;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
