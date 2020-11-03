package com.jzsf.tuitor.rpcDomain.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/14
 */
public class ArticleReq implements Serializable {

    private static final long serialVersionUID = 5641380589982113508L;

    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String title;
    private String content;
    private List<String> articleTagList;

    public List<String> getArticleTagList() {
        return articleTagList;
    }

    public void setArticleTagList(List<String> articleTagList) {
        this.articleTagList = articleTagList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
