package com.jzsf.tuitor.service;

import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Service
public interface MailService {

    /**
     * 发送简单文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @return 邮件是否发送成功
     */
    boolean sendSimpleMail(String to, String subject, String content);
}
