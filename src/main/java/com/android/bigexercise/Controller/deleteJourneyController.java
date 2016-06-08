package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.ContentService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.CreateJourneyRequest;
import com.android.bigexercise.Request.DeleteJourneyRequest;
import com.android.bigexercise.Response.CreateJourneyResponse;
import com.android.bigexercise.Response.DeleteJourneyResponse;
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

/**
 * Created by NeilHY on 2016/5/28.
 */
@RestController
public class deleteJourneyController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = requestMappingUrl.DELETEJOURNEYURL,method = RequestMethod.POST)
    @ResponseBody
    public String deleteJourney(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        DeleteJourneyRequest deleteJourneyRequest;
        DeleteJourneyResponse deleteJourneyResponse=new DeleteJourneyResponse();
        boolean isSuccess=true;
        Long userId = null;
        try{
            inputStream=httpRequest.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                deleteJourneyRequest= gson.fromJson(requestBody,DeleteJourneyRequest.class);
                if (deleteJourneyRequest == null|| deleteJourneyRequest.getUserId() == null ||deleteJourneyRequest.getUserId()<=0L ||deleteJourneyRequest.getContentId()==null||
                        deleteJourneyRequest.getContentId()<=0L) {

                    isSuccess=false;
                } else  {
                    userId =contentService.deleteJourney(deleteJourneyRequest);
                    if(userId==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            deleteJourneyResponse.setUserId(0L);
            deleteJourneyResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
            return gson.toJson(deleteJourneyResponse);
        }
        if(isSuccess && userId!=null){
            deleteJourneyResponse.setUserId(userId);
            deleteJourneyResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            deleteJourneyResponse.setUserId(0L);
            deleteJourneyResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(deleteJourneyResponse);
    }
}
