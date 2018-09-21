package com.qgx.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *@Author: goxcheer
 *@Date:21:42 2018/7/29
 *@email:604721660@qq.com
 *@decription: 博客实体
 */
public class Blog implements Serializable {

    private Integer id; // 编号
    private String title; // 博客标题
    private String summary; // 摘要
    private Date releaseDate; // 发布日期
    private Integer clickCount; // 查看次数
    private Integer replyCount; // 回复次数
    private String content; // 博客内容
    private String contentNoTag; // 博客内容，无网页标签 Lucene分词用到
    private BlogCategory blogCategory; // 博客类型
    private String keyWord; // 关键字 空格隔开
    private Integer blogCount; // 博客数量 非博客实际属性 主要是 根据发布日期归档查询数量用到
    private String releaseDateStr; // 发布日期的字符串 只取年和月
    private List<String> imgList = new LinkedList<>(); //博客中的图片，便于展示
    private Integer praiseCount; //点赞次数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public String getContent() {
        return content;
    }

    public String getContentNoTag() {
        return contentNoTag;
    }
    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogCategory getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(BlogCategory blogCategory) {
        this.blogCategory = blogCategory;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }
}
