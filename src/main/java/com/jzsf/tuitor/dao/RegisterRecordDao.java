package com.jzsf.tuitor.dao;

import com.jzsf.tuitor.pojo.RegisterRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Repository
public interface RegisterRecordDao extends JpaRepository<RegisterRecord, String> {

    /**
     * 根据邮件查询
     *
     * @param email 查询条件
     * @return
     */
    List<RegisterRecord> findByEmail(String email);

    /**
     * 根据注册用户名获取注册记录
     *
     * @param username
     * @return
     */
    RegisterRecord findByUsername(String username);
}
