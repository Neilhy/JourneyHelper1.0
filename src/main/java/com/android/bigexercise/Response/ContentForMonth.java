package com.android.bigexercise.Response;

import java.util.ArrayList;

/**
 * Created by NeilHY on 2016/6/4.
 */
public class ContentForMonth {
    private Long contentId;
    private String title;
    private String content;
    private int year;
    private int month;
    private int day;
    private ArrayList<String> photoUrlList;

    public ContentForMonth() {
    }

    public ContentForMonth(Long contentId, String title, String content, int year, int month, int day) {
        this.contentId = contentId;
        this.title = title;
        this.content = content;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
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

    public ArrayList<String> getPhotoUrlList() {
        return photoUrlList;
    }

    public void setPhotoUrlList(ArrayList<String> photoUrlList) {
        this.photoUrlList = photoUrlList;
    }
}
