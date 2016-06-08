package com.android.bigexercise.BasicClass.service;

import com.android.bigexercise.BasicClass.model.content;
import com.android.bigexercise.BasicClass.model.photo;
import com.android.bigexercise.BasicClass.model.user;
import com.android.bigexercise.BasicClass.repository.ContentTbRepository;
import com.android.bigexercise.BasicClass.repository.PhotoTbRepository;
import com.android.bigexercise.BasicClass.repository.UserTbRepository;
import com.android.bigexercise.Request.*;
import com.android.bigexercise.Response.ContentForMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NeilHY on 2016/5/28.
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentTbRepository contentTbRepository;
    @Autowired
    private UserTbRepository userTbRepository;
    @Autowired
    private PhotoTbRepository photoTbRepository;

    @Override
    public Long modifyContent(ModifyContentRequest modifyContentRequest) {
        if (modifyContentRequest.getUserId() != null && modifyContentRequest.getContentId() != null) {
            user user = userTbRepository.findOne(modifyContentRequest.getUserId());
            content content = contentTbRepository.findOne(modifyContentRequest.getContentId());
            if (user != null && content!=null) {
                if (modifyContentRequest.getTitle() != null) {
                    content.setTitle(modifyContentRequest.getTitle());
                }
                if (modifyContentRequest.getContent() != null) {
                    content.setContent(modifyContentRequest.getContent());
                }
                contentTbRepository.save(content);
                return user.getUserId();
            }
        }
        return null;
    }

    @Override
    public Long createJourney(CreateJourneyRequest createJourneyRequest) {
        if (createJourneyRequest.getUserId() != null) {
            user user = userTbRepository.findOne(createJourneyRequest.getUserId());
            Long contentId;
            do {
                contentId = getUniqueID.getID();
            } while (contentTbRepository.findOne(contentId)!=null);
            if (user != null ) {
                content contentNew = new content(contentId,createJourneyRequest.getContent(), createJourneyRequest.getTitle(),
                        createJourneyRequest.getYear(), createJourneyRequest.getMonth(), createJourneyRequest.getDay(), user);
                content contentDone = contentTbRepository.save(contentNew);
                ArrayList<String> photos=createJourneyRequest.getPhoto();
                if (photos != null) {
                    for (String photoNew : photos) {
                        byte[] picture = DecodePicture.decodeFromBase64(photoNew);
                        Long photoId = CreateFile.createPhotoFilePath(picture);
                        if (photoId != null) {
                            photo photo = new photo(photoId ,user, contentDone);
                            photoTbRepository.save(photo);
                        }
                    }
                }
                return user.getUserId();
            }
        }
        return null;
    }

    @Override
    public Long deleteJourney(DeleteJourneyRequest deleteJourneyRequest) {
        if (deleteJourneyRequest.getUserId() != null && deleteJourneyRequest.getContentId() != null) {
            user user = userTbRepository.findOne(deleteJourneyRequest.getUserId());
            content content = contentTbRepository.findOne(deleteJourneyRequest.getContentId());
            if (user != null && content!=null) {
                List<photo> photoList = photoTbRepository.findByContent_ContentId(content.getContentId());
                for (photo photoOld : photoList) {
                    photoTbRepository.delete(photoOld);
                    File photoFile = new File(CreateFile.getPhotoFile().getAbsolutePath(), photoOld.getPhotoId().toString()+".journeypic");
                    if (photoFile.exists()) {
                        photoFile.delete();
                    }
                }
                contentTbRepository.delete(content);
                return user.getUserId();
            }
        }
        return null;
    }

    @Override
    public Long addPhoto(AddPhotoRequest addPhotoRequest) {
        if (addPhotoRequest.getUserId() != null && addPhotoRequest.getContentId() != null) {
            user user = userTbRepository.findOne(addPhotoRequest.getUserId());
            content content = contentTbRepository.findOne(addPhotoRequest.getContentId());
            if (user != null && content != null) {
                ArrayList<String> photos=addPhotoRequest.getPhoto();
                if (photos != null) {
                    for (String photoNew : photos) {
                        byte[] picture = DecodePicture.decodeFromBase64(photoNew);
                        Long photoId = CreateFile.createPhotoFilePath(picture);
                        if (photoId != null) {
                            photo photo = new photo(photoId, user, content);
                            photoTbRepository.save(photo);
                        }
                    }
                }
                return user.getUserId();
            }
        }
        return null;
    }

    @Override
    public Long deletePhoto(DeletePhotoRequest deletePhotoRequest) {
        if (deletePhotoRequest.getUserId() != null && deletePhotoRequest.getContentId() != null) {
            user user = userTbRepository.findOne(deletePhotoRequest.getUserId());
            content content = contentTbRepository.findOne(deletePhotoRequest.getContentId());
            Long photoId = CreateFile.getPhotoIdfromUrl(deletePhotoRequest.getPhotoUrl());
            photo photo = photoTbRepository.findOne(photoId);
            if (user != null && content != null && photo!=null) {
                photoTbRepository.delete(photo);
                File photoFile = new File(CreateFile.getPhotoFile().getAbsolutePath(), photoId.toString()+".journeypic");
                if (photoFile.exists()) {
                    photoFile.delete();
                }
                return user.getUserId();
            }
        }
        return null;
    }

    @Override
    public ArrayList<ContentForMonth> getMonthJourney(GetMonthJourneyRequest getMonthJourneyRequest) {
        if (getMonthJourneyRequest.getUserId() != null && getMonthJourneyRequest.getYear() >0 || getMonthJourneyRequest.getMonth() >0) {
            List<content> contentArrayList = contentTbRepository.findByUser_UserIdAndYearAndMonth(getMonthJourneyRequest.getUserId(), getMonthJourneyRequest.getYear(), getMonthJourneyRequest.getMonth());
            if (contentArrayList != null) {
                ArrayList<ContentForMonth> contentForMonthArrayList = new ArrayList<>();
                for (content con : contentArrayList) {
                    ContentForMonth contentForMonth = new ContentForMonth(con.getContentId(), con.getTitle(), con.getContent(), con.getYear(), con.getMonth(), con.getDay());
                    List<photo> photoList = photoTbRepository.findByContent_ContentId(con.getContentId());
                    if (photoList != null) {
                        contentForMonth.setPhotoUrlList( getPhotoStringListfromIdList(photoList) );
                    }
                    contentForMonthArrayList.add(contentForMonth);
                }
                return contentForMonthArrayList;
            }
        }
        return null;
    }

    private ArrayList<String> getPhotoStringListfromIdList(List<photo> photoIdList) {
        ArrayList<String> photoStringList=new ArrayList<>();
        for (photo photoOne : photoIdList) {
            String photoUrl = CreateFile.getPhotoUrlfromId(photoOne.getPhotoId());
            photoStringList.add(photoUrl);
        }
        return photoStringList;
    }

    @Override
    public ArrayList<ContentForMonth> getTenJourneies(GetTenJourneiesRecentRequest getTenJourneiesRecentRequest) {
        if (getTenJourneiesRecentRequest.getUserId() != null && getTenJourneiesRecentRequest.getEmailAddr()!=null) {
            List<content> contentArrayList = contentTbRepository.findByUser_UserId(getTenJourneiesRecentRequest.getUserId());
            if (contentArrayList != null) {
                ArrayList<ContentForMonth> contentForMonthArrayList = new ArrayList<>();
                for (int k=1, i=contentArrayList.size()-1;i>=0 && k<=10;i--,k++) {
                    content con= contentArrayList.get(i);
                    ContentForMonth contentForMonth = new ContentForMonth(con.getContentId(), con.getTitle(), con.getContent(), con.getYear(), con.getMonth(), con.getDay());
                    List<photo> photoList = photoTbRepository.findByContent_ContentId(con.getContentId());
                    if (photoList != null) {
                        contentForMonth.setPhotoUrlList( getPhotoStringListfromIdList(photoList) );
                    }
                    contentForMonthArrayList.add(contentForMonth);
                }
                return contentForMonthArrayList;
            }
        }
        return null;
    }
}
