package com.qgx.service.impl;

import com.qgx.dao.LinkDao;
import com.qgx.entity.Link;
import com.qgx.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 *@Author: goxcheer
 *@Date:20:35 2018/7/29
 *@email:604721660@qq.com
 *@decription: 友情链接Service实现类
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkDao linkDao;

    @Override
    public List<Link> listLink() {
        return linkDao.listLink();
    }
}
