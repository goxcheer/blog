package com.qgx.entity;

import java.io.Serializable;

/**
 *@Author: goxcheer
 *@Date:16:19 2018/7/29
 *@email:604721660@qq.com
 *@decription: 友情链接
 */
public class Link implements Serializable {

    private Integer id;
    private String linkName; //链接名称
    private String linkUrl;  //链接地址
    private Integer priority; //优先级

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", linkName='" + linkName + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", priority=" + priority +
                '}';
    }
}
