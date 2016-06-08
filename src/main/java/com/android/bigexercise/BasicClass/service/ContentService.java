package com.android.bigexercise.BasicClass.service;

import com.android.bigexercise.Request.*;
import com.android.bigexercise.Response.ContentForMonth;

import java.util.ArrayList;

/**
 * Created by NeilHY on 2016/5/28.
 */
public interface ContentService {
    Long modifyContent(ModifyContentRequest modifyContentRequest);

    Long createJourney(CreateJourneyRequest createJourneyRequest);

    Long deleteJourney(DeleteJourneyRequest deleteJourneyRequest);

    Long addPhoto(AddPhotoRequest addPhotoRequest);

    Long deletePhoto(DeletePhotoRequest deletePhotoRequest);

    ArrayList<ContentForMonth> getMonthJourney(GetMonthJourneyRequest getMonthJourneyRequest);

    ArrayList<ContentForMonth> getTenJourneies(GetTenJourneiesRecentRequest getTenJourneiesRecentRequest);
}
