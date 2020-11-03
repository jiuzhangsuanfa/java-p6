package com.jzsf.tuitor.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author by plain yuan
 * @since 2020/04/12
 * 文章实体类
 */

@Entity
@Table(name = "article")
public class Article implements Serializable {

    private static final long serialVersionUID = 7754919014646385390L;
    @Id
    @Column(name = "id", nullable = false, length = 48)
    private String id;

    @Column(name = "user_id", nullable = false, length = 48)
    private String userId;

    @Column(name = "publish_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp publishTime;

    @Column(name = "title", length = 16)
    private String title;

    @Column(name = "content")
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publish_time) {
        this.publishTime = publish_time;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
