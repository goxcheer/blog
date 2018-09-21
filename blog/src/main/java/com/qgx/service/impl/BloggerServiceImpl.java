package com.qgx.service.impl;

import com.qgx.dao.BloggerDao;
import com.qgx.entity.Blogger;
import com.qgx.service.BloggerService;
import com.qgx.util.RedisUtil;
import com.qgx.util.SpringContextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *@Author: goxcheer
 *@Date:11:21 2018/7/28
 *@email:604721660@qq.com
 *@decription: 博主service实现类
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {
    @Resource
    private BloggerDao bloggerDao;

    @Override
    public Blogger getBloggerByUsername(String username) {
        return bloggerDao.getBloggerByUsername(username);
    }

    @Override
    public Blogger getBlogger() {
        return bloggerDao.getBlogger();
    }
}
