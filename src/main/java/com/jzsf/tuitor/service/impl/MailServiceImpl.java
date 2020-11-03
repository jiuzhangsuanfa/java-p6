package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public boolean sendSimpleMail(String to, String subject, String content) {
        //创建SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();
        //发送人
        message.setFrom(from);
        //接收人
        message.setTo(to);
        //主题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        try {
            //发送
            mailSender.send(message);
        } catch (Exception e) {
            // 发送失败
            return false;
        }
        return true;
    }

}
