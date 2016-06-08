package com.android.bigexercise.BasicClass.repository;

import com.android.bigexercise.BasicClass.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by NeilHY on 2016/5/27.
 */
public interface UserTbRepository extends JpaRepository<user,Long> {
    user findByEmailAddr(String emailAddr);

//    @Transactional
//    @Modifying
//    @Query("update user u set u.icon=:icon where u.userId=:userId")
//    int upDateIcon(@Param("userId") Long userId, @Param("icon") String icon);

//    @Modifying
//    @Query("update user u set u.icon=?2 where u.userId=?1")
//    int upDateIcon(Long userId, String icon);
}
