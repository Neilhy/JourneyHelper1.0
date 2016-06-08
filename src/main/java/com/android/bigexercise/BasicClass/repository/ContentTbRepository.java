package com.android.bigexercise.BasicClass.repository;

import com.android.bigexercise.BasicClass.model.content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by NeilHY on 2016/5/27.
 */
public interface ContentTbRepository extends JpaRepository<content,Long>{
//    @Query("select con from content con where con.userId= :userId and con.year= :year and con.month= :month")
//    List<content> findByUserIdAndYearAndMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);
    List<content> findByUser_UserIdAndYearAndMonth(Long userId,int year, int month);

    List<content> findByUser_UserId(Long userId);
}
