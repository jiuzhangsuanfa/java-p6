package com.jzsf.tuitor.control;

import com.jzsf.tuitor.common.token.JwtIgnore;
import com.jzsf.tuitor.common.utils.JwtTokenUtil;
import com.jzsf.tuitor.common.utils.MD5Utils;
import com.jzsf.tuitor.pojo.User;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.common.ResultCode;
import com.jzsf.tuitor.rpcDomain.req.LoginReq;
import com.jzsf.tuitor.rpcDomain.req.RegisterReq;
import com.jzsf.tuitor.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by plain yuan
 * @since 2020/04/12
 * 用户登录操作核心控制器
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping(value = "/getCaptcha", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @JwtIgnore
    public RespResult getCaptcha(@RequestBody RegisterReq reqInfo) {
        return userService.beforeRegister(reqInfo);
    }

    @PostMapping(value = "/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @JwtIgnore
    public RespResult register(@RequestBody RegisterReq reqInfo) {
        try {
            // 先校验验证码
            if (!userService.checkCaptcha(reqInfo)) {
                return new RespResult(ResultCode.WRONG_CAPTCHA);
            }
        }catch (Exception e){
            return new RespResult(ResultCode.CAN_NOT_REGISTER);
        }
        // 执行注册
        return userService.registerUser(reqInfo);
    }


    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @JwtIgnore
    public RespResult login(HttpServletResponse response, @RequestBody LoginReq loginReq) {
        User user = userService.getByUsername(loginReq.getUsername());
        if (!userService.checkValid(user)) {
            return new RespResult(ResultCode.USER_INVALID);
        }
        // check password
        if (!StringUtils.equals(user.getPassword(), MD5Utils.getMD5(loginReq.getPassword()))) {
            return new RespResult(ResultCode.USER_LOGIN_ERROR);
        }
        // generate token
        String token = JwtTokenUtil.createJWT(user.getId(), user.getUsername());
        logger.info("用户 : " + user.getUsername() + " 登录成功");
        logger.info("token : " + token);

        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        Map<String, String> result = new HashMap();
        result.put("token", token);
        return new RespResult(ResultCode.LOGIN_SUCCESS, result);
    }


}
