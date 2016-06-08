package com.android.bigexercise.BasicClass.service;

import com.android.bigexercise.BasicClass.model.content;
import com.android.bigexercise.BasicClass.model.user;
import com.android.bigexercise.BasicClass.model.yearAndmonth;
import com.android.bigexercise.BasicClass.repository.UserTbRepository;
import com.android.bigexercise.Request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import static com.android.bigexercise.BasicClass.service.CreateFile.getIconFile;
import static com.android.bigexercise.BasicClass.service.CreateFile.getPhotoFile;
import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * Created by NeilHY on 2016/5/28.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserTbRepository userTbRepository;


    @Override
    public user login(LoginRequest loginRequest) {
        if (loginRequest.getEmailAddr() == null) {
            return null;
        }
        user user = userTbRepository.findByEmailAddr(loginRequest.getEmailAddr());
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(loginRequest.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public Long createUser(SignUpRequest signUpRequest) {
        if (signUpRequest.getEmailAddr() == null || signUpRequest.getPassword() == null || signUpRequest.getUserName() == null) {
            return null;
        }
        user userOld = userTbRepository.findByEmailAddr(signUpRequest.getEmailAddr());
        if (userOld != null) {
            return null;
        }
        Long userId=getUniqueID.getID();
        user userNew = new user(userId, signUpRequest.getEmailAddr(), signUpRequest.getUserName(), signUpRequest.getIcon(), signUpRequest.getPassword());
        user user=userTbRepository.save(userNew);
        return user.getUserId();
    }

    @Override
    public boolean deleteUser(DeleteUserRequest deleteUserRequest) {
        if (deleteUserRequest.getUserId() == null) {
            return false;
        }
        user userOne = userTbRepository.findOne(deleteUserRequest.getUserId());
        if( userOne != null && userOne.getEmailAddr().equals(deleteUserRequest.getEmailAddr())) {
            userTbRepository.delete(userOne);
            return true;
        }
        return false;
    }

    @Override
    public Long updateIcon(UpdateIconRequest updateIconRequest) {
        if (updateIconRequest.getUserId() == null) {
            return null;
        }
        user userOne = userTbRepository.findOne(updateIconRequest.getUserId());
        if (userOne == null || !userOne.getEmailAddr().equals(updateIconRequest.getEmailAddr())) {
            return null;
        }
        byte[] icon=DecodePicture.decodeFromBase64(updateIconRequest.getIcon());
        String iconPath=CreateFile.createIconFilePath(icon);
        if (iconPath != null) {
            String iconOldPath=userOne.getIcon();
            userOne.setIcon(iconPath);
            user userNew = userTbRepository.save(userOne);
            //在数据库修改动作！！！！！！！！
//            if (userTbRepository.upDateIcon(userOne.getUserId(), iconPath) > 0) {
            if(userNew.getIcon().equals(iconPath)) {
                if (iconOldPath != null && !iconOldPath.equals("")) {
                    File iconFile = new File(getPhotoFile().getAbsolutePath(), iconOldPath);
                    if (iconFile.exists()) {
                        iconFile.delete();
                    }
                }
                return userNew.getUserId();
            }
        }
        return null;
    }

    @Override
    public Long updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        if (updatePasswordRequest.getUserId() == null) {
            return null;
        }
        user userOne = userTbRepository.findOne(updatePasswordRequest.getUserId());
        if( userOne != null && userOne.getEmailAddr().equals(updatePasswordRequest.getEmailAddr()) && updatePasswordRequest.getPassword()!=null && !updatePasswordRequest.getPassword().equals("")) {
            userOne.setPassword(updatePasswordRequest.getPassword());

            userOne = userTbRepository.save(userOne);
            return userOne.getUserId();
        }
        return null;
    }

    @Override
    public Long updateUsername(UpdateUsernameRequest updateUsernameRequest) {
        if (updateUsernameRequest.getUserId() == null) {
            return null;
        }
        user userOne = userTbRepository.findOne(updateUsernameRequest.getUserId());
        if( userOne != null && userOne.getEmailAddr().equals(updateUsernameRequest.getEmailAddr()) && updateUsernameRequest.getUserName()!=null && !updateUsernameRequest.getUserName().equals("")) {
            userOne.setUserName(updateUsernameRequest.getUserName());

            userOne = userTbRepository.save(userOne);
            return userOne.getUserId();
        }
        return null;
    }

    @Override
    public yearAndmonth getYearAndMonth(GetYearAndMonthRequest getYearAndMonthRequest) {
        if (getYearAndMonthRequest.getUserId() == null) {
            return null;
        }
        user userOne = userTbRepository.findOne(getYearAndMonthRequest.getUserId());
        if( userOne != null && userOne.getEmailAddr().equals(getYearAndMonthRequest.getEmailAddr())) {
            Set<content> contentSet=userOne.getContentSet();
            if(contentSet==null || contentSet.isEmpty())return null;

            ArrayList<Integer> yearList = new ArrayList<>();
            HashMap<Integer, ArrayList<Integer>> monthList = new HashMap<>();
            for (content con : contentSet) {
                if (con != null) {
                    int year=con.getYear();
                    int month=con.getMonth();
                    ArrayList<Integer> months;
                    if( ! yearList.contains(year)){
                        yearList.add(year);
                        months = new ArrayList<>();
                        //把键值对添加到map中
                        months.add(month);
                        monthList.put(year, months);
                    }else{
                        months = monthList.get(year);
                        if( ! months.contains(month)) {
                            months.add(month);
                            Collections.sort(months);
                            monthList.put(year, months);
                        }
                    }
                }
            }
            if( !yearList.isEmpty()){
                Collections.sort(yearList);
            }
            return new yearAndmonth(yearList, monthList);
        }
        return null;
    }
}
