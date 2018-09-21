package com.qgx.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *@Author: goxcheer
 *@Date:21:02 2018/7/29
 *@email:604721660@qq.com
 *@decription: 博客类别实体
 */
public class BlogCategory implements Serializable {

    @SerializedName("blogCategoryId")
    private Integer id;
    private String categoryName; //博客类别名称
    private Integer blogCount; //分类下博客数量
    private Integer priority; //优先级


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "BlogCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", blogCount=" + blogCount +
                ", priority=" + priority +
                '}';
    }
}
