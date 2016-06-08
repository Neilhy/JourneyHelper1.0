package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.model.user;
import com.android.bigexercise.BasicClass.service.UserService;
import com.android.bigexercise.Request.LoginRequest;
import com.android.bigexercise.Response.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import com.android.bigexercise.Response.responseConstant.respConstant;

/**
 * Created by NeilHY on 2016/5/28.
 */
@RestController
public class loginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = requestMappingUrl.LOGINURL,method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        InputStream inputStream;
        String requestBody;
        Gson gson=new GsonBuilder().create();
        LoginRequest loginRequest;
        LoginResponse loginResponse=new LoginResponse();
        boolean isSuccess=true;
        user user = null;
        try{
            inputStream=httpRequest.getInputStream();
            if (inputStream == null) {
                isSuccess=false;
            }else{
                requestBody = IOUtils.toString(inputStream, "utf-8");
                loginRequest= gson.fromJson(requestBody,LoginRequest.class);
                if (loginRequest == null||loginRequest.getEmailAddr() == null || loginRequest.getPassword() == null) {
                    isSuccess=false;
                } else  {
                    user = userService.login(loginRequest);//在数据库里查询此用户，并且判断密码是否正确
                    if(user==null){ //若此用户不存在或者密码错误，都会返回空
                        isSuccess=false;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            loginResponse.setUserId(0L);
            loginResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
            return gson.toJson(loginResponse);
        }
        if(isSuccess){
            loginResponse.setUserId(user.getUserId());
            loginResponse.setUserName(user.getUserName());
            if (user.getIcon() != null) {

                loginResponse.setIcon(user.getIcon());
            }
            loginResponse.setEmailAddr(user.getEmailAddr());
            loginResponse.setStatus(respConstant.RESPONSE_SUCCESS_STATUS);
        }else{
            loginResponse.setUserId(0L);
            loginResponse.setStatus(respConstant.RESPONSE_FAILURE_STATUS);
        }
        return gson.toJson(loginResponse);
    }

}
