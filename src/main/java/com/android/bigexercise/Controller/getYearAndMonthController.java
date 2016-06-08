package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.model.user;
import com.android.bigexercise.BasicClass.model.yearAndmonth;
import com.android.bigexercise.BasicClass.service.UserService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.GetYearAndMonthRequest;
import com.android.bigexercise.Request.LoginRequest;
import com.android.bigexercise.Response.GetYearAndMonthResponse;
import com.android.bigexercise.Response.LoginResponse;
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
 * Created by NeilHY on 2016/5/29.
 */
@RestController
public class getYearAndMonthController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = requestMappingUrl.GETYEARANDMONTH,method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        GetYearAndMonthRequest getYearAndMonthRequest = null;
        GetYearAndMonthResponse getYearAndMonthResponse=new GetYearAndMonthResponse();
        boolean isSuccess=true;
        yearAndmonth yearMonth = null;
        try{
            inputStream=httpRequest.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                getYearAndMonthRequest= gson.fromJson(requestBody,GetYearAndMonthRequest.class);
                if (getYearAndMonthRequest == null||getYearAndMonthRequest.getEmailAddr() == null || getYearAndMonthRequest.getUserId() == null
                        ||getYearAndMonthRequest.getUserId()<=0L||getYearAndMonthRequest.getEmailAddr().equals("")) {
                    isSuccess=false;
                } else  {
                    yearMonth = userService.getYearAndMonth(getYearAndMonthRequest);
                    if(yearMonth==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            getYearAndMonthResponse.setUserId(0L);
            getYearAndMonthResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
            return gson.toJson(getYearAndMonthResponse);
        }
        if(isSuccess && getYearAndMonthRequest!=null){
            getYearAndMonthResponse.setUserId(getYearAndMonthRequest.getUserId());
            getYearAndMonthResponse.setYearAndmonth(yearMonth);
            getYearAndMonthResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            getYearAndMonthResponse.setUserId(0L);
            getYearAndMonthResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(getYearAndMonthResponse);
    }
}
