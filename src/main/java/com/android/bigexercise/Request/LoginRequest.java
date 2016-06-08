package com.android.bigexercise.Request;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class LoginRequest {
    private String emailAddr;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }
}
