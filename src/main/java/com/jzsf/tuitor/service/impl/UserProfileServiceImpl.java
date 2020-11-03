package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.dao.UserProfileDao;
import com.jzsf.tuitor.pojo.Address;
import com.jzsf.tuitor.pojo.User;
import com.jzsf.tuitor.pojo.UserPreference;
import com.jzsf.tuitor.pojo.UserProfile;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.common.ResultCode;
import com.jzsf.tuitor.rpcDomain.req.UserProfileReq;
import com.jzsf.tuitor.rpcDomain.resp.UserProfileResp;
import com.jzsf.tuitor.service.AddressService;
import com.jzsf.tuitor.service.UserPreferenceService;
import com.jzsf.tuitor.service.UserProfileService;
import com.jzsf.tuitor.service.UserService;
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
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile, String>
        implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Override
    protected JpaRepository getRepository() {
        return userProfileDao;
    }

    @Override
    public RespResult getUserProfileInfo(String userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        UserProfile userProfile = userProfileDao.findById(userId).get();
        Address address = addressService.findById(userId).get();
        UserPreference userPreference = userPreferenceService.findById(userId).get();

        UserProfileResp respInfo = new UserProfileResp();

        BeanUtils.copyProperties(user, respInfo);
        BeanUtils.copyProperties(address, respInfo);
        BeanUtils.copyProperties(userProfile, respInfo);
        BeanUtils.copyProperties(userPreference, respInfo);

        return new RespResult(ResultCode.SUCCESS, respInfo);
    }

    @Override
    public RespResult updateUserProfile(String userId, UserProfileReq userProfileReq) {
        Optional<User> userOptional = userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }

        User user = userOptional.get();
        Address address = addressService.findById(userId).get();
        UserProfile userProfile = userProfileDao.findById(userId).get();
        UserPreference userPreference = userPreferenceService.findById(userId).get();

        BeanUtils.copyProperties(userProfileReq, user);
        BeanUtils.copyProperties(userProfileReq, address);
        BeanUtils.copyProperties(userProfileReq, userProfile);
        BeanUtils.copyProperties(userProfileReq, userPreference);

        userService.save(user);
        addressService.save(address);
        userProfileDao.save(userProfile);
        userPreferenceService.save(userPreference);

        return new RespResult(ResultCode.SUCCESS);
    }

}
