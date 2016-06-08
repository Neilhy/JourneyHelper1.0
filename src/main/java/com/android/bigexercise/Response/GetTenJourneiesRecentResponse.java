package com.android.bigexercise.Response;

import java.util.ArrayList;

/**
 * Created by NeilHY on 2016/6/6.
 */
public class GetTenJourneiesRecentResponse {
    private Long userId;
    private String status;
    private ArrayList<ContentForMonth> contentForMonths;

    public GetTenJourneiesRecentResponse() {
    }

    public GetTenJourneiesRecentResponse(Long userId, String status, ArrayList<ContentForMonth> contentForMonths) {
        this.userId = userId;
        this.status = status;
        this.contentForMonths = contentForMonths;
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

    public ArrayList<ContentForMonth> getContentForMonths() {
        return contentForMonths;
    }

    public void setContentForMonths(ArrayList<ContentForMonth> contentForMonths) {
        this.contentForMonths = contentForMonths;
    }
}
