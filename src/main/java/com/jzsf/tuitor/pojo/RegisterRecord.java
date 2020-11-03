package com.jzsf.tuitor.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */

@Entity
@Table(name = "register_record")
public class RegisterRecord implements Serializable {

    private static final long serialVersionUID = -4928118636119976369L;

    @Id
    @Column(name = "id", length = 64)
    private String id;

    @Column(name = "username", length = 16)
    private String username;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "captcha", length = 16)
    private String captcha;

    @Column(name = "send_time")
    private Date sendTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
