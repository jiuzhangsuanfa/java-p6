package com.jzsf.tuitor.rpcDomain.resp;

import java.io.Serializable;
import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/15
 */
public class UserCenterDTOResp implements Serializable {

    private static final long serialVersionUID = 4265242922477483929L;

    private String username;
    private String personalProfile;
    private String provinceAndCity;
    private List<String> userTagList;
    private List<ArticleResp> articleList;

    public String getProvinceAndCity() {
        return provinceAndCity;
    }

    public void setProvinceAndCity(String provinceAndCity) {
        this.provinceAndCity = provinceAndCity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public List<String> getUserTagList() {
        return userTagList;
    }

    public void setUserTagList(List<String> userTagList) {
        this.userTagList = userTagList;
    }

    public List<ArticleResp> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleResp> articleList) {
        this.articleList = articleList;
    }

}
