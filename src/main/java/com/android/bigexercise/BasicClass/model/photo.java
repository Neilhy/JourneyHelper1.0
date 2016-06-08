package com.android.bigexercise.BasicClass.model;

import javax.persistence.*;

import static javafx.scene.input.KeyCode.L;

/**
 * Created by NeilHY on 2016/5/27.
 */
@Entity
@Table(name="photoTable")
public class photo {

    @Id
    @Column(name = "photoId")
    private Long photoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId",referencedColumnName = "userId")
    private user user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="contentId",referencedColumnName = "contentId")
    private content content;

    public photo() {
    }

    public photo(Long photoId,user user,content content) {
        this.photoId=photoId;
        this.user=user;
        this.content=content;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public com.android.bigexercise.BasicClass.model.user getUser() {
        return user;
    }

    public void setUser(com.android.bigexercise.BasicClass.model.user user) {
        this.user = user;
    }

    public com.android.bigexercise.BasicClass.model.content getContent() {
        return content;
    }

    public void setContent(com.android.bigexercise.BasicClass.model.content content) {
        this.content = content;
    }
}
