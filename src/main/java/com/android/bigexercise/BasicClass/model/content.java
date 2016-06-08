package com.android.bigexercise.BasicClass.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NeilHY on 2016/5/27.
 */
@Entity
@Table(name="contentTable")
public class content {

    @Id
    @Column(name = "contentId")
    private Long contentId;

    @Column(name="content")
    private String content;

    @Column(name="title")
    private String title;

    @Column(name="createYear")
    private int year;

    @Column(name="createMonth")
    private int month;

    @Column(name="createDay")
    private int day;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private user user;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "contentId", referencedColumnName = "contentId")
    private Set<photo> photoSet = new HashSet<>();

    public content() {
    }

    public content(Long contentId,String content, String title, int year, int month, int day,user user) {
        this.contentId=contentId;
        this.content = content;
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
        this.user=user;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public com.android.bigexercise.BasicClass.model.user getUser() {
        return user;
    }

    public void setUser(com.android.bigexercise.BasicClass.model.user user) {
        this.user = user;
    }

    public Set<photo> getPhotoSet() {
        return photoSet;
    }

    public void setPhotoSet(Set<photo> photoSet) {
        this.photoSet = photoSet;
    }
}
