package com.android.bigexercise.Request;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class UpdateUsernameRequest {
    private Long userId;
    private String emailAddr;
    private String userName;

    public UpdateUsernameRequest() {
    }

    public UpdateUsernameRequest(Long userId, String emailAddr, String userName) {
        this.userId = userId;
        this.emailAddr = emailAddr;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
