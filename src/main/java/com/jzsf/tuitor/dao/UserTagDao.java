package com.jzsf.tuitor.dao;

import com.jzsf.tuitor.pojo.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Repository
public interface UserTagDao extends JpaRepository<UserTag, String> {
    /**
     * 根据用户id，获取用户标签
     *
     * @param userId
     * @return
     */
    @Query("select tagName from UserTag where userId=?1")
    List<String> getUserTagList(String userId);
}
