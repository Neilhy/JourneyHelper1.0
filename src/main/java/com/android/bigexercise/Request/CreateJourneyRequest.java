package com.android.bigexercise.Request;

import java.util.ArrayList;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class CreateJourneyRequest {
    private Long userId;
    private String title;
    private String content;
    private int year;
    private int month;
    private int day;
    private ArrayList<String> photo;

    public CreateJourneyRequest() {
    }

    public CreateJourneyRequest(Long userId, String title, String content, ArrayList<String> photo, int year, int month, int day) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.photo = photo;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<String> photo) {
        this.photo = photo;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
