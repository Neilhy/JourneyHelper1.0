package com.android.bigexercise.Request;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class UpdateIconRequest {
    private Long userId;
    private String emailAddr;
    private String icon;

    public UpdateIconRequest() {
    }

    public UpdateIconRequest(Long userId, String emailAddr, String icon) {
        this.userId = userId;
        this.emailAddr = emailAddr;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
