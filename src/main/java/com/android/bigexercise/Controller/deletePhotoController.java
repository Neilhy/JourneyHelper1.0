package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.ContentService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.DeleteJourneyRequest;
import com.android.bigexercise.Request.DeletePhotoRequest;
import com.android.bigexercise.Response.DeleteJourneyResponse;
import com.android.bigexercise.Response.DeletePhotoResponse;
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
public class deletePhotoController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = requestMappingUrl.DELETEPHOTOURL,method = RequestMethod.POST)
    @ResponseBody
    public String deletePhoto(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        DeletePhotoRequest deletePhotoRequest;
        DeletePhotoResponse deletePhotoResponse=new DeletePhotoResponse();
        boolean isSuccess=true;
        Long userId = null;
        try{
            inputStream=httpRequest.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                deletePhotoRequest= gson.fromJson(requestBody,DeletePhotoRequest.class);
                if (deletePhotoRequest == null|| deletePhotoRequest.getUserId() == null ||deletePhotoRequest.getUserId()<=0L ||deletePhotoRequest.getContentId()==null||
                        deletePhotoRequest.getContentId()<=0L || deletePhotoRequest.getPhotoUrl()==null||deletePhotoRequest.getPhotoUrl().equals("")) {
                    isSuccess=false;
                } else  {
                    userId =contentService.deletePhoto(deletePhotoRequest);
                    if(userId==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            deletePhotoResponse.setUserId(0L);
            deletePhotoResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
            return gson.toJson(deletePhotoResponse);
        }
        if(isSuccess && userId!=null){
            deletePhotoResponse.setUserId(userId);
            deletePhotoResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            deletePhotoResponse.setUserId(0L);
            deletePhotoResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(deletePhotoResponse);
    }
}
