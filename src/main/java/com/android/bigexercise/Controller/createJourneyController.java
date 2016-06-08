package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.ContentService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.CreateJourneyRequest;
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
public class createJourneyController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = requestMappingUrl.CREATEJOURNEYURL,method = RequestMethod.POST)
    @ResponseBody
    public String createJourney(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        CreateJourneyRequest createJourneyRequest;
        CreateJourneyResponse createJourneyResponse=new CreateJourneyResponse();
        boolean isSuccess=true;
        Long userId = null;
        try{
            inputStream=httpRequest.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                createJourneyRequest= gson.fromJson(requestBody,CreateJourneyRequest.class);
                if (createJourneyRequest == null|| createJourneyRequest.getUserId() == null ||createJourneyRequest.getUserId()<=0L ||createJourneyRequest.getYear()==0
                        ||createJourneyRequest.getMonth()==0||createJourneyRequest.getDay()==0) {

                    isSuccess=false;
                } else  {
                    userId =contentService.createJourney(createJourneyRequest);
                    if(userId==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            createJourneyResponse.setUserId(0L);
            createJourneyResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
            return gson.toJson(createJourneyResponse);
        }
        if(isSuccess && userId!=null){
            createJourneyResponse.setUserId(userId);
            createJourneyResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            createJourneyResponse.setUserId(0L);
            createJourneyResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(createJourneyResponse);
    }
}
