package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.model.user;
import com.android.bigexercise.BasicClass.service.UserService;
import com.android.bigexercise.Request.LoginRequest;
import com.android.bigexercise.Request.SignUpRequest;
import com.android.bigexercise.Response.LoginResponse;
import com.android.bigexercise.Response.SignUpResponse;
import com.android.bigexercise.Response.responseConstant.respConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
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
public class signUpController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = requestMappingUrl.SIGNUPURL, method = RequestMethod.POST)
    @ResponseBody
    public String signUp(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        SignUpRequest signUpRequest = new SignUpRequest();
        SignUpResponse signUpResponse=new SignUpResponse();
        boolean isSuccess=true;
        Long userId = null;
        try{
            inputStream=request.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                signUpRequest= gson.fromJson(requestBody,SignUpRequest.class);
                if (signUpRequest == null||signUpRequest.getEmailAddr() == null || signUpRequest.getPassword() == null ||signUpRequest.getUserName()==null
                        || signUpRequest.getUserName().equals("")|| signUpRequest.getPassword().equals("")||signUpRequest.getEmailAddr().equals("")) {
                    isSuccess=false;
                } else  {
                    userId = userService.createUser(signUpRequest);
                    if(userId==null){
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        if(isSuccess && userId!=null && signUpRequest.getEmailAddr() != null){
            signUpResponse.setUserId(userId);
            signUpResponse.setEmailAddr(signUpRequest.getEmailAddr());
            signUpResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            signUpResponse.setUserId(0L);
            signUpResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(signUpResponse);
    }
}
