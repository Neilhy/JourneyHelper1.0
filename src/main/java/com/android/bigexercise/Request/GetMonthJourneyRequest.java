package com.android.bigexercise.Request;

/**
 * Created by NeilHY on 2016/6/4.
 */
public class GetMonthJourneyRequest {
    private Long userId;
    private int year;
    private int month;

    public GetMonthJourneyRequest() {
    }

    public GetMonthJourneyRequest(Long userId, int year, int month) {
        this.userId = userId;
        this.year = year;
        this.month = month;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
