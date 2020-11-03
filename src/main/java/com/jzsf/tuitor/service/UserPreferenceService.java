package com.jzsf.tuitor.service;

import com.jzsf.tuitor.pojo.UserPreference;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.req.UserPreferenceReq;
import com.jzsf.tuitor.rpcDomain.resp.UserPreferenceResp;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public interface UserPreferenceService extends BaseService<UserPreference, String> {

    /**
     * 根据用户名，获取偏好设置
     *
     * @param userId
     * @return
     */
    RespResult<UserPreferenceResp> getByUserId(String userId);

    /**
     * 根据userId更新个人偏好设置
     *
     * @param req
     * @param userId
     * @return
     */
    RespResult updateSetting(UserPreferenceReq req, String userId);

}
