package com.jzsf.tuitor.dao;

import com.jzsf.tuitor.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {

    /**
     * 根据用户名，获取entity
     *
     * @param username 用户名
     * @return 当前User
     */
    User getByUsername(String username);

    @Query("select u from User u where u.username = ?1 and u.email = ?2")
    User getByRegisterInfo(String username, String email);
}
