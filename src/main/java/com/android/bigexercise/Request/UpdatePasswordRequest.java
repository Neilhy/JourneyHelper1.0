package com.android.bigexercise.Request;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class UpdatePasswordRequest {
    private Long userId;
    private String emailAddr;
    private String password;

    public UpdatePasswordRequest() {
    }

    public UpdatePasswordRequest(Long userId, String emailAddr, String password) {
        this.userId = userId;
        this.emailAddr = emailAddr;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
