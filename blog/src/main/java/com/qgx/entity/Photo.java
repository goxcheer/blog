package com.qgx.entity;

import java.io.Serializable;

/**
 *@Author: goxcheer
 *@Date:20:37 2018/9/8
 *@email:604721660@qq.com
 *@decription: 个人相册实体
 */
public class Photo implements Serializable {

    private int id;
    private String url; //图片地址
    private String uploadDate; //上传时间
    private String title;  //照片标题
    private String summary;  //照片简介

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
