package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.ContentService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.CreateJourneyRequest;
import com.android.bigexercise.Request.ModifyContentRequest;
import com.android.bigexercise.Response.CreateJourneyResponse;
import com.android.bigexercise.Response.ModifyContentResponse;
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
public class modifyContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = requestMappingUrl.MODIFYCONTENTURL,method = RequestMethod.POST)
    @ResponseBody
    public String modifyContent(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        ModifyContentRequest modifyContentRequest;
        ModifyContentResponse modifyContentResponse=new ModifyContentResponse();
        boolean isSuccess=true;
        Long userId = null;
        try{
            inputStream=httpRequest.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                modifyContentRequest= gson.fromJson(requestBody,ModifyContentRequest.class);
                if (modifyContentRequest == null|| modifyContentRequest.getUserId() == null ||modifyContentRequest.getUserId()<=0L
                        ||modifyContentRequest.getContentId()==null||modifyContentRequest.getContentId()<=0) {

                    isSuccess=false;
                } else  {
                    userId =contentService.modifyContent(modifyContentRequest);
                    if(userId==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            modifyContentResponse.setUserId(0L);
            modifyContentResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
            return gson.toJson(modifyContentResponse);
        }
        if(isSuccess && userId!=null){
            modifyContentResponse.setUserId(userId);
            modifyContentResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            modifyContentResponse.setUserId(0L);
            modifyContentResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(modifyContentResponse);
    }
}
