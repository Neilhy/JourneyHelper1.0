package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.ContentService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.GetMonthJourneyRequest;
import com.android.bigexercise.Request.GetTenJourneiesRecentRequest;
import com.android.bigexercise.Response.ContentForMonth;
import com.android.bigexercise.Response.GetMonthJourneyResponse;
import com.android.bigexercise.Response.GetTenJourneiesRecentResponse;
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
 * Created by NeilHY on 2016/6/6.
 */
@RestController
public class getTenJourneiesRecentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = requestMappingUrl.GETTENJOURNEIESRECENT,method = RequestMethod.POST)
    @ResponseBody
    public String createJourney(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        GetTenJourneiesRecentRequest getTenJourneiesRecentRequest = null;
        GetTenJourneiesRecentResponse getTenJourneiesRecentResponse=new GetTenJourneiesRecentResponse();
        boolean isSuccess=true;
        ArrayList<ContentForMonth> contentForMonthArrayList = null;
        try{
            inputStream=httpRequest.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                getTenJourneiesRecentRequest= gson.fromJson(requestBody,GetTenJourneiesRecentRequest.class);
                if (getTenJourneiesRecentRequest == null|| getTenJourneiesRecentRequest.getUserId() == null ||getTenJourneiesRecentRequest.getUserId()<=0L
                        ||getTenJourneiesRecentRequest.getEmailAddr()==null||getTenJourneiesRecentRequest.getEmailAddr().equals("")) {

                    isSuccess=false;
                } else  {
                    contentForMonthArrayList=contentService.getTenJourneies(getTenJourneiesRecentRequest);
                    if(contentForMonthArrayList==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            getTenJourneiesRecentResponse.setUserId(0L);
            getTenJourneiesRecentResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
            return gson.toJson(getTenJourneiesRecentResponse);
        }
        if(isSuccess){
            getTenJourneiesRecentResponse.setUserId(getTenJourneiesRecentRequest.getUserId());
            getTenJourneiesRecentResponse.setContentForMonths(contentForMonthArrayList);
            getTenJourneiesRecentResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            getTenJourneiesRecentResponse.setUserId(0L);
            getTenJourneiesRecentResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(getTenJourneiesRecentResponse);
    }
}
