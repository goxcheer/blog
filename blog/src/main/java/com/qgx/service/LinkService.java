package com.qgx.service;

import com.qgx.entity.Link;

import java.util.List;

/**
 *@Author: goxcheer
 *@Date:20:33 2018/7/29
 *@email:604721660@qq.com
 *@decription: 友情链接service接口
 */
public interface LinkService {
    /**
     * 获取所有的友情链接
     * @return
     */
    public List<Link> listLink();
}
