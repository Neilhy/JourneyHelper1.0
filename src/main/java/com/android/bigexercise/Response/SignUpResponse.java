package com.android.bigexercise.Response;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class SignUpResponse {
    private String emailAddr;
    private String status;
    private Long userId;

    public SignUpResponse() {
    }

    public SignUpResponse(String emailAddr, String status,Long userId) {
        this.emailAddr = emailAddr;
        this.status = status;
        this.userId=userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
