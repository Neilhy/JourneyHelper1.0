package com.android.bigexercise.Request;

/**
 * Created by NeilHY on 2016/6/6.
 */
public class GetTenJourneiesRecentRequest {
    private Long userId;
    private String emailAddr;

    public GetTenJourneiesRecentRequest() {
    }

    public GetTenJourneiesRecentRequest(Long userId, String emailAddr) {
        this.userId = userId;
        this.emailAddr = emailAddr;
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
}
