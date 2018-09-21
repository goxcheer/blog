package com.qgx.dao;

import com.qgx.entity.Blogger;

/**
 *@Author: goxcheer
 *@Date:11:17 2018/7/28
 *@email:604721660@qq.com
 *@decription: 博主Dao接口
 */
public interface BloggerDao {
    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    public Blogger getBloggerByUsername(String username);

    /**
     * 直接获取用户
     * @return
     */
    public Blogger getBlogger();
}
