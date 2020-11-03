package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.dao.UserPreferenceDao;
import com.jzsf.tuitor.pojo.User;
import com.jzsf.tuitor.pojo.UserPreference;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.common.ResultCode;
import com.jzsf.tuitor.rpcDomain.req.UserPreferenceReq;
import com.jzsf.tuitor.rpcDomain.resp.UserPreferenceResp;
import com.jzsf.tuitor.service.UserPreferenceService;
import com.jzsf.tuitor.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public class UserPreferenceServiceImpl
        extends BaseServiceImpl<UserPreference, String>
        implements UserPreferenceService {

    @Autowired
    UserPreferenceDao userPreferenceDao;

    @Autowired
    UserService userService;

    @Override
    protected JpaRepository getRepository() {
        return userPreferenceDao;
    }

    @Override
    public RespResult<UserPreferenceResp> getByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return new RespResult<>(ResultCode.PARAM_IS_BLANK);
        }
        Optional<User> user = userService.findById(userId);
        if (!user.isPresent()) {
            return new RespResult(ResultCode.FAIL, "没有查到该用户的设置信息");
        }
        Optional<UserPreference> entity = userPreferenceDao.findById(user.get().getId());
        UserPreferenceResp resp = new UserPreferenceResp();
        BeanUtils.copyProperties(entity.get(), resp);
        return new RespResult(ResultCode.SUCCESS, resp);
    }

    @Override
    public RespResult updateSetting(UserPreferenceReq req, String userId) {
        UserPreference userPreference = new UserPreference();
        BeanUtils.copyProperties(req, userPreference);
        userPreference.setUserId(userId);
        userPreferenceDao.save(userPreference);
        return new RespResult(ResultCode.SUCCESS);
    }
}
