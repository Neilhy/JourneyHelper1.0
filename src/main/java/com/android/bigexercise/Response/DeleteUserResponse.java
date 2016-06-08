package com.android.bigexercise.Response;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class DeleteUserResponse {
   private Long userId;
   private String status;

    public DeleteUserResponse() {
    }

    public DeleteUserResponse(Long userId, String status) {
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
