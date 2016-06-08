package com.android.bigexercise.BasicClass.repository;

import com.android.bigexercise.BasicClass.model.content;
import com.android.bigexercise.BasicClass.model.photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by NeilHY on 2016/5/27.
 */
public interface PhotoTbRepository extends JpaRepository<photo,Long>{
    List<photo> findByContent_ContentId(Long contentId);

}
