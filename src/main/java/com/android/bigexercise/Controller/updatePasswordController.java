package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.UserService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.UpdateIconRequest;
import com.android.bigexercise.Request.UpdatePasswordRequest;
import com.android.bigexercise.Response.UpdatePasswordResponse;
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
public class updatePasswordController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = requestMappingUrl.UPDATEPASSWORDURL, method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        UpdatePasswordResponse updatePasswordResponse = new UpdatePasswordResponse();
        boolean isSuccess=true;
        Long userId=null;
        try{
            inputStream=request.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                updatePasswordRequest= gson.fromJson(requestBody,UpdatePasswordRequest.class);
                if (updatePasswordRequest == null||updatePasswordRequest.getEmailAddr() == null || updatePasswordRequest.getUserId() == null ||updatePasswordRequest.getPassword()==null
                        || updatePasswordRequest.getEmailAddr().equals("")|| updatePasswordRequest.getPassword().equals("")) {
                    isSuccess=false;
                } else  {
                    userId = userService.updatePassword(updatePasswordRequest);
                    if(userId==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        if(isSuccess && userId!=null && updatePasswordRequest.getEmailAddr() != null){
            updatePasswordResponse.setUserId(userId);
            updatePasswordResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            updatePasswordResponse.setUserId(0L);
            updatePasswordResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(updatePasswordResponse);

    }
}
