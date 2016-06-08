package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.UserService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.SignUpRequest;
import com.android.bigexercise.Request.UpdateIconRequest;
import com.android.bigexercise.Response.SignUpResponse;
import com.android.bigexercise.Response.UpdateIconResponse;
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
public class updateIconController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = requestMappingUrl.UPDATEICONURL, method = RequestMethod.POST)
    @ResponseBody
    public String updateIcon(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        UpdateIconRequest updateIconRequest = new UpdateIconRequest();
        UpdateIconResponse updateIconResponse=new UpdateIconResponse();
        boolean isSuccess=true;
        Long userId = null;
        try{
            inputStream=request.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                updateIconRequest= gson.fromJson(requestBody,UpdateIconRequest.class);
                if (updateIconRequest == null||updateIconRequest.getEmailAddr() == null || updateIconRequest.getUserId() == null ||updateIconRequest.getIcon()==null
                        || updateIconRequest.getEmailAddr().equals("")|| updateIconRequest.getIcon().equals("")) {
                    isSuccess=false;
                } else  {
                    userId = userService.updateIcon(updateIconRequest);
                    if(userId==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        if(isSuccess && userId!=null && updateIconRequest.getEmailAddr() != null){
            updateIconResponse.setUserId(userId);
            updateIconResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            updateIconResponse.setUserId(0L);
            updateIconResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(updateIconResponse);
    }
}
