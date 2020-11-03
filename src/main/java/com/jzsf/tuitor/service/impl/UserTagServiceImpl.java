package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.dao.UserTagDao;
import com.jzsf.tuitor.pojo.UserTag;
import com.jzsf.tuitor.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public class UserTagServiceImpl
        extends BaseServiceImpl<UserTag, String>
        implements UserTagService {

    @Autowired
    private UserTagDao userTagDao;

    @Override
    protected JpaRepository getRepository() {
        return userTagDao;
    }

    @Override
    public List<String> getUserTagList(String userId) {
        return userTagDao.getUserTagList(userId);
    }
}
