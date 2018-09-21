package com.qgx.entity;

import java.io.Serializable;

/**
 *@Author: goxcheer
 *@Date:11:15 2018/7/28
 *@email:604721660@qq.com
 *@decription: 博主实体类
 */
public class Blogger implements Serializable {

    private Integer id; //编号
    private String username; //用户名
    private String password; //密码
    private String profile; //个人简介
    private String nickname; //昵称
    private String signature; //签名
    private String image; //头像

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Blogger{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profile='" + profile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", signature='" + signature + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
