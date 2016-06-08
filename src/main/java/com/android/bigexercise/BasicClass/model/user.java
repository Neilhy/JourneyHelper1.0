package com.android.bigexercise.BasicClass.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NeilHY on 2016/5/27.
 */
@Entity
@Table(name="userTable")
public class user {
    @Id
    @Column(name = "userId")
    private Long userId;

    @Column(name="emailAddr")
    private String emailAddr;

    @Column(name = "userName")
    private String userName;

    @Column(name="icon")
    private String icon;

    @Column(name="password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private Set<content> contentSet=new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    @JoinColumn(name="userId",referencedColumnName = "userId")
    private Set<photo> photoSet = new HashSet<>();

    public user() {
    }

    public user(Long userId,String emailAddr, String userName, String icon, String password) {
        this.userId=userId;
        this.emailAddr = emailAddr;
        this.userName = userName;
        this.icon = icon;
        this.password = password;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<content> getContentSet() {
        return contentSet;
    }

    public void setContentSet(Set<content> contentSet) {
        this.contentSet = contentSet;
    }

    public Set<photo> getPhotoSet() {
        return photoSet;
    }

    public void setPhotoSet(Set<photo> photoSet) {
        this.photoSet = photoSet;
    }
}
