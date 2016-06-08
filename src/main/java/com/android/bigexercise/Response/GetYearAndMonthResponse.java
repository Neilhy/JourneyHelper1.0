package com.android.bigexercise.Response;

import com.android.bigexercise.BasicClass.model.yearAndmonth;

/**
 * Created by NeilHY on 2016/5/29.
 */
public class GetYearAndMonthResponse {
    private Long userId;
    private yearAndmonth yearAndmonth;
    private String status;

    public GetYearAndMonthResponse() {
    }

    public GetYearAndMonthResponse(Long userId, com.android.bigexercise.BasicClass.model.yearAndmonth yearAndmonth, String status) {
        this.userId = userId;
        this.yearAndmonth = yearAndmonth;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public com.android.bigexercise.BasicClass.model.yearAndmonth getYearAndmonth() {
        return yearAndmonth;
    }

    public void setYearAndmonth(com.android.bigexercise.BasicClass.model.yearAndmonth yearAndmonth) {
        this.yearAndmonth = yearAndmonth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
