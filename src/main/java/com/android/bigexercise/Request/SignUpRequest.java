package com.android.bigexercise.Request;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class SignUpRequest {
    private String userName;
    private String emailAddr;
    private String password;
    private String icon;

    public SignUpRequest() {
    }

    public SignUpRequest(String userName, String emailAddr, String password,String icon) {
        this.userName = userName;
        this.emailAddr = emailAddr;
        this.password = password;
        this.icon=icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
