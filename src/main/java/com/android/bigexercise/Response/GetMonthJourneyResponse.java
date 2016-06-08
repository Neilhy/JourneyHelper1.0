package com.android.bigexercise.Response;

import java.util.ArrayList;

/**
 * Created by NeilHY on 2016/6/4.
 */
public class GetMonthJourneyResponse {
    private Long userId;
    private ArrayList<ContentForMonth> contentForMonths;
    private String status;

    public GetMonthJourneyResponse() {
    }

    public GetMonthJourneyResponse(Long userId, ArrayList<ContentForMonth> contentForMonths,String status) {
        this.userId = userId;
        this.contentForMonths = contentForMonths;
        this.status=status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ArrayList<ContentForMonth> getContentForMonths() {
        return contentForMonths;
    }

    public void setContentForMonths(ArrayList<ContentForMonth> contentForMonths) {
        this.contentForMonths = contentForMonths;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
