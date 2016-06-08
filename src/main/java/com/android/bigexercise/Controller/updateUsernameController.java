package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.UserService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.UpdatePasswordRequest;
import com.android.bigexercise.Request.UpdateUsernameRequest;
import com.android.bigexercise.Response.UpdatePasswordResponse;
import com.android.bigexercise.Response.UpdateUsernameResponse;
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
public class updateUsernameController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = requestMappingUrl.UPDATEUSERNAMEURL, method = RequestMethod.POST)
    @ResponseBody
    public String updateUsername(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        UpdateUsernameRequest updateUsernameRequest = new UpdateUsernameRequest();
        UpdateUsernameResponse updateUsernameResponse = new UpdateUsernameResponse();
        boolean isSuccess=true;
        Long userId=null;
        try{
            inputStream=request.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                updateUsernameRequest= gson.fromJson(requestBody,UpdateUsernameRequest.class);
                if (updateUsernameRequest == null||updateUsernameRequest.getEmailAddr() == null || updateUsernameRequest.getUserId() == null ||updateUsernameRequest.getUserName()==null
                        || updateUsernameRequest.getEmailAddr().equals("")|| updateUsernameRequest.getUserName().equals("")) {
                    isSuccess=false;
                } else  {
                    userId = userService.updateUsername(updateUsernameRequest);
                    if(userId==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        if(isSuccess && userId!=null && updateUsernameRequest.getEmailAddr() != null){
            updateUsernameResponse.setUserId(userId);
            updateUsernameResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            updateUsernameResponse.setUserId(0L);
            updateUsernameResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(updateUsernameResponse);
    }
}
