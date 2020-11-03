package com.jzsf.tuitor.rpcDomain.req;

import java.io.Serializable;

/**
 * @author by plain yuan
 * @since 2020/04/12
 * 携带注册信息请求的entity
 */
public class RegisterReq implements Serializable {

    private static final long serialVersionUID = -690477244058984705L;

    private String username;
    private String password;
    private String email;
    private String captcha;

    public RegisterReq() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
