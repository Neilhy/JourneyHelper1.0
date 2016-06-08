package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.UserService;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import com.android.bigexercise.Request.DeleteUserRequest;
import com.android.bigexercise.Request.UpdateUsernameRequest;
import com.android.bigexercise.Response.DeleteUserResponse;
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
public class deleteUserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = requestMappingUrl.DELETEUSERURL, method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        DeleteUserResponse deleteUserResponse = new DeleteUserResponse();
        boolean isSuccess=true;
        try{
            inputStream=request.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                deleteUserRequest= gson.fromJson(requestBody,DeleteUserRequest.class);
                if (deleteUserRequest == null||deleteUserRequest.getEmailAddr() == null || deleteUserRequest.getUserId() == null || deleteUserRequest.getEmailAddr().equals("")) {
                    isSuccess=false;
                } else  {
                    if(!userService.deleteUser(deleteUserRequest)){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        if(isSuccess &&  deleteUserRequest.getEmailAddr() != null){
            deleteUserResponse.setUserId(deleteUserRequest.getUserId());
            deleteUserResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            deleteUserResponse.setUserId(0L);
            deleteUserResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(deleteUserResponse);
    }
}
