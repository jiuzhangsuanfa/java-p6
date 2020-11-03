package com.jzsf.tuitor.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author by plain yuan
 * @since 2020/04/12
 * 用户实体
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -4537601996396295771L;

    @Id
    @Column(name = "id", nullable = false, length = 48)
    private String id;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "is_verified",length = 4)
    private String isVerified;

    @Column(name = "email", length = 32)
    private String email;

    @Column(name = "phone_number",length = 32)
    private String phoneNumber;

    @Column(name = "area_number",length = 16)
    private String areaNumber;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = passWord;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }
}
