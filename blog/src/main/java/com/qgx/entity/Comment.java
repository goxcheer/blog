package com.qgx.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *@Author: goxcheer
 *@Date:19:39 2018/8/23
 *@email:604721660@qq.com
 *@decription: 博客实体类
 */
public class Comment implements Serializable {

    private Integer id;
    private String userIp; //用户id地址
    private String userName; //用户名称
    private Blog blog;  //所属博客
    private String content; //文本
    private Date commentDate; //评论日期
    private Integer state; //状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
