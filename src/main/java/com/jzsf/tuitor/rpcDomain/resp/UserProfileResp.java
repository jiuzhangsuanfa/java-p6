package com.jzsf.tuitor.rpcDomain.resp;

import java.io.Serializable;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
public class UserProfileResp implements Serializable {


    private static final long serialVersionUID = -943303242229672346L;

    private String username;
    private String email;
    private String personalProfile;
    private String country;
    private String province;
    private String city;
    private String streetAddress;
    private String areaNumber;
    private String phoneNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserProfileResp{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", personalProfile='" + personalProfile + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", areaNumber='" + areaNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}
