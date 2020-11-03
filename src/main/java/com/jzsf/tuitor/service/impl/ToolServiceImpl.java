/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.common.utils.RandomCaptcha;
import com.jzsf.tuitor.rpcDomain.req.RegisterReq;
import com.jzsf.tuitor.service.MailService;
import com.jzsf.tuitor.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author senyang
 * @version $Id: ToolServiceImpl.java, v 0.1 2020年04月24日 12:31 AM senyang Exp $
 */
@Service
public class ToolServiceImpl implements ToolService {
    @Autowired
    private MailService mailService;

    private String captcha = "";

    /**
     * Setter method for property <tt>captcha</tt>.
     *
     * @param captcha value to be assigned to property captcha
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }


    @Override
    public String getCaptcha() {
        return RandomCaptcha.get();
    }

    @Override
    public boolean sendRegisterMail(RegisterReq req) {
        captcha = this.getCaptcha();
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("您好，").append(req.getUsername()).append("，您的验证码是 ：").append(captcha);
        String subject = "新用户注册";
        return this.mailService.sendSimpleMail(req.getEmail(), subject, contentBuilder.toString());
    }

}