package com.android.bigexercise.Response;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class AddPhotoResponse {
    private Long userId;
    private String status;

    public AddPhotoResponse() {
    }

    public AddPhotoResponse(Long userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
