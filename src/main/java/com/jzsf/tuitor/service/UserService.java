package com.jzsf.tuitor.service;

import com.jzsf.tuitor.pojo.User;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.req.RegisterReq;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Service
public interface UserService extends BaseService<User, String> {

    /**
     * 检查邮箱是否注册，未注册则发送验证码
     *
     * @param reqInfo 用户注册时的信息
     * @return
     */
    RespResult beforeRegister(RegisterReq reqInfo);

    /**
     * 校验验证码
     *
     * @param reqInfo
     * @return
     */
    boolean checkCaptcha(RegisterReq reqInfo) throws Exception;

    /**
     * 注册新用户
     *
     * @param reqInfo
     * @return 注册动作信息
     */
    RespResult registerUser(RegisterReq reqInfo);

    /**
     * 查看当前账户是否有效
     *
     * @param user
     * @return
     */
    boolean checkValid(User user);

    /**
     * 根据用户名获取user
     *
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 获取当前用户的用户中心信息
     *
     * @param userId
     * @return
     */
    RespResult getAccountCenterInfo(String userId);
}
