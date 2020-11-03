package com.jzsf.tuitor.control;

import com.jzsf.tuitor.common.utils.BeanUtil;
import com.jzsf.tuitor.common.utils.JwtTokenUtil;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.common.ResultCode;
import com.jzsf.tuitor.rpcDomain.req.UserPreferenceReq;
import com.jzsf.tuitor.rpcDomain.req.UserProfileReq;
import com.jzsf.tuitor.service.UserPreferenceService;
import com.jzsf.tuitor.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/13
 * 账户操作控制器
 */
@CrossOrigin
@Controller
@RequestMapping("/account/settings")
public class AccountSettingController {

    private Logger logger = LoggerFactory.getLogger(AccountSettingController.class);

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserProfileService userProfileService;


    @PostMapping(value = "/notice/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult showNoticeConfig(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.getByUserId(userId);
    }

    @PostMapping(value = "/notice/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult updateNoticeConfig(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                                         @RequestBody UserPreferenceReq noticeConfigReq) {
        List<String> validateMsg = BeanUtil.validateProperty(noticeConfigReq);
        if (validateMsg.size() > 0) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, validateMsg);
        }
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.updateSetting(noticeConfigReq, userId);
    }

    @PostMapping(value = "/profile/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult showProfile(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userProfileService.getUserProfileInfo(userId);
    }

    @PostMapping(value = "/profile/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult updateProfile(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                                    @RequestBody UserProfileReq userProfileReq) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        List<String> validateMsg = BeanUtil.validateProperty(userProfileReq);
        if (validateMsg.size() > 0) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, validateMsg);
        }
        return userProfileService.updateUserProfile(userId, userProfileReq);
    }

    @PostMapping(value = "/profile/upload/avatar", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult upload(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                             @RequestParam("file") MultipartFile file, HttpServletRequest req) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);

        // 判断上传文件是否合法
        if (file.isEmpty()) {
            return new RespResult(ResultCode.PARAM_IS_BLANK);
        }
        // 获取文件存储路径（绝对路径）
        String path = req.getServletContext().getRealPath("/file");
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例，每个用户一个文件夹，进行区分
        File filePath = new File(userId + path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            logger.info("创建目录" + filePath);
        }
        // 写入文件
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            logger.error("写入失败：", e.getMessage());
        }

        return new RespResult(ResultCode.SUCCESS);
    }

    @PostMapping(value = "/profile/load/avatar", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult loadAvatar(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        // todo读取未完成
        return new RespResult(ResultCode.SUCCESS);
    }

}
