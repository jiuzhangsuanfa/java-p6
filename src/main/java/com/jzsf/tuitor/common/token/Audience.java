package com.jzsf.tuitor.common.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author by plain yuan
 * @since 2020/04/13
 * 配置信息实体类，获取JWT配置
 */
@Component
public class Audience {

    @Value("${audience.clientID}")
    private String clientId;
    @Value("${audience.base64Secret}")
    private String base64Secret;
    @Value("${audience.name}")
    private String name;
    @Value("${audience.expiresSecond}")
    private int expiresSecond;

    public Audience() {
    }

    public Audience(String clientId, String base64Secret, String name, int expiresSecond) {
        this.clientId = clientId;
        this.base64Secret = base64Secret;
        this.name = name;
        this.expiresSecond = expiresSecond;
    }


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpiresSecond() {
        return expiresSecond;
    }

    public void setExpiresSecond(int expiresSecond) {
        this.expiresSecond = expiresSecond;
    }
}
