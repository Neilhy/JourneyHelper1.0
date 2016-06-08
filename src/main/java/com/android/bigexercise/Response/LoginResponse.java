package com.android.bigexercise.Response;

import java.util.ArrayList;

/**
 * Created by NeilHY on 2016/5/27.
 */
public class LoginResponse {
    private Long userId;
    private String emailAddr;
    private String userName;
    private String icon;
    //private ArrayList<YearAndMonth> yearAndMonths;
    private String status;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
