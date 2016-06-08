package com.android.bigexercise.BasicClass.service;

import com.android.bigexercise.BasicClass.model.user;
import com.android.bigexercise.BasicClass.model.yearAndmonth;
import com.android.bigexercise.Request.*;

/**
 * Created by NeilHY on 2016/5/28.
 */
public interface UserService {
    user login(LoginRequest loginRequest);

    Long createUser(SignUpRequest signUpRequest);

    boolean deleteUser(DeleteUserRequest deleteUserRequest);

    Long updateIcon(UpdateIconRequest updateIconRequest);

    Long updatePassword(UpdatePasswordRequest updatePasswordRequest);

    Long updateUsername(UpdateUsernameRequest updateUsernameRequest);

    yearAndmonth getYearAndMonth(GetYearAndMonthRequest getYearAndMonthRequest);
}
