package com.qgx.service;

import com.qgx.entity.Blogger;

/**
 *@Author: goxcheer
 *@Date:11:19 2018/7/28
 *@email:604721660@qq.com
 *@decription: 博主service接口
 */
public interface BloggerService {
    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public Blogger getBloggerByUsername(String username);

    /**
     * 直接获取当前博主信息
     * @return
     */
    public Blogger getBlogger();
}
