package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.ContentService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.CreateJourneyRequest;
import com.android.bigexercise.Request.GetMonthJourneyRequest;
import com.android.bigexercise.Response.ContentForMonth;
import com.android.bigexercise.Response.CreateJourneyResponse;
import com.android.bigexercise.Response.GetMonthJourneyResponse;
import com.android.bigexercise.Response.responseConstant.respConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by NeilHY on 2016/6/4.
 */
@RestController
public class getMonthJourneyController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = requestMappingUrl.GETMONTHJOURNEY,method = RequestMethod.POST)
    @ResponseBody
    public String createJourney(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        GetMonthJourneyRequest getMonthJourneyRequest = null;
        GetMonthJourneyResponse getMonthJourneyResponse=new GetMonthJourneyResponse();
        boolean isSuccess=true;
        ArrayList<ContentForMonth> contentForMonthArrayList = null;
        try{
            inputStream=httpRequest.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                getMonthJourneyRequest= gson.fromJson(requestBody,GetMonthJourneyRequest.class);
                if (getMonthJourneyRequest == null|| getMonthJourneyRequest.getUserId() == null ||getMonthJourneyRequest.getUserId()<=0L
                        ||getMonthJourneyRequest.getYear()<=0 ||getMonthJourneyRequest.getMonth()<=0) {

                    isSuccess=false;
                } else  {
                    contentForMonthArrayList=contentService.getMonthJourney(getMonthJourneyRequest);
                    if(contentForMonthArrayList==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            getMonthJourneyResponse.setUserId(0L);
            getMonthJourneyResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
            return gson.toJson(getMonthJourneyResponse);
        }
        if(isSuccess){
            getMonthJourneyResponse.setUserId(getMonthJourneyRequest.getUserId());
            getMonthJourneyResponse.setContentForMonths(contentForMonthArrayList);
            getMonthJourneyResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            getMonthJourneyResponse.setUserId(0L);
            getMonthJourneyResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(getMonthJourneyResponse);
    }
}
