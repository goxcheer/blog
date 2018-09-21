package com.qgx.dao;

import com.qgx.entity.Link;

import java.util.List;

/**
 *@Author: goxcheer
 *@Date:20:28 2018/7/29
 *@email:604721660@qq.com
 *@decription: 友情链接Dao接口
 */
public interface LinkDao {
    /**
     * 查询所有的友情链接
     * @return
     */
    public List<Link>listLink();
}
