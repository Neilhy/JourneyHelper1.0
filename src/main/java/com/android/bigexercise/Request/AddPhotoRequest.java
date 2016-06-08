package com.android.bigexercise.Request;

import java.util.ArrayList;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class AddPhotoRequest {
    private Long userId;
    private Long contentId;
    private ArrayList<String> photo;

    public AddPhotoRequest() {
    }

    public AddPhotoRequest(Long userId, Long contentId, ArrayList<String> photo) {
        this.userId = userId;
        this.contentId = contentId;
        this.photo = photo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public ArrayList<String> getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<String> photo) {
        this.photo = photo;
    }
}
