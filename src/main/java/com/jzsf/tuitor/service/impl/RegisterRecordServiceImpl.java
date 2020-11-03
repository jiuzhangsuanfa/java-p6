package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.dao.RegisterRecordDao;
import com.jzsf.tuitor.pojo.RegisterRecord;
import com.jzsf.tuitor.service.RegisterRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Service
public class RegisterRecordServiceImpl
        extends BaseServiceImpl<RegisterRecord, String>
        implements RegisterRecordService {

    @Autowired
    private RegisterRecordDao registerRecordDao;

    @Override
    protected JpaRepository<RegisterRecord, String> getRepository() {
        return registerRecordDao;
    }

    @Override
    public int checkRegister(String email, String username) {
        // 增强返回逻辑，指明被注册的详细信息
        return registerRecordDao.findByEmail(email).size() >= 1 ? 2 :
                registerRecordDao.findByUsername(username) != null ? 1 : 0;
    }

    @Override
    public String getCaptchaByUsername(String username) throws Exception {
        RegisterRecord registerRecord = registerRecordDao.findByUsername(username);
        if (registerRecord == null) {
            System.err.println("查询到了空的注册记录");
            throw  new Exception();
        }
        return registerRecord.getCaptcha();
    }
}
