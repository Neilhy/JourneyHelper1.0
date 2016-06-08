package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.ContentService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.AddPhotoRequest;
import com.android.bigexercise.Request.CreateJourneyRequest;
import com.android.bigexercise.Response.AddPhotoResponse;
import com.android.bigexercise.Response.CreateJourneyResponse;
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
public class addPhotoController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = requestMappingUrl.ADDPHOTOURL,method = RequestMethod.POST)
    @ResponseBody
    public String addPhoto(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        AddPhotoRequest addPhotoRequest;
        AddPhotoResponse addPhotoResponse=new AddPhotoResponse();
        boolean isSuccess=true;
        Long userId = null;
        try{
            inputStream=httpRequest.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                addPhotoRequest= gson.fromJson(requestBody,AddPhotoRequest.class);
                if (addPhotoRequest == null|| addPhotoRequest.getUserId() == null ||addPhotoRequest.getUserId()<=0L
                        ||addPhotoRequest.getContentId()==null||addPhotoRequest.getContentId()<=0L||addPhotoRequest.getPhoto()==null
                        ||addPhotoRequest.getPhoto().isEmpty()) {
                    isSuccess=false;
                } else  {
                    userId =contentService.addPhoto(addPhotoRequest);
                    if(userId==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            addPhotoResponse.setUserId(0L);
            addPhotoResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
            return gson.toJson(addPhotoResponse);
        }
        if(isSuccess && userId!=null){
            addPhotoResponse.setUserId(userId);
            addPhotoResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            addPhotoResponse.setUserId(0L);
            addPhotoResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(addPhotoResponse);
    }
}
