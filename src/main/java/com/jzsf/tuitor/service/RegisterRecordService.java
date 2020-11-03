package com.jzsf.tuitor.service;

import com.jzsf.tuitor.pojo.RegisterRecord;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */

@Service
public interface RegisterRecordService extends BaseService<RegisterRecord, String> {

    /**
     * 查看当前邮箱或用户名是否被注册
     *
     * @param email
     * @param username
     * @return 0:未注册 1:用户名占用 2:邮箱占用
     */
    int checkRegister(String email, String username);

    /**
     * 根据用户名获取注册时的验证码
     * @param username
     * @return
     */
    String getCaptchaByUsername(String username) throws Exception;
}
