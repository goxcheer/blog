package com.qgx.service.impl;

import com.qgx.dao.BlogCategoryDao;
import com.qgx.entity.BlogCategory;
import com.qgx.service.BlogCategoryService;
import com.qgx.util.RedisUtil;
import com.qgx.util.SpringContextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *@Author: goxcheer
 *@Date:10:06 2018/8/5
 *@email:604721660@qq.com
 *@decription: 博客类别Service的实现类
 */
@Service("blogCategoryService")
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Resource
    private BlogCategoryDao blogCategoryDao;

    @Override
    public List<BlogCategory> listBlogCategory() {
        return blogCategoryDao.listBlogCategory();
    }

    @Override
    public List<BlogCategory> listBlogCategoryByPage(Map<String, Object> map) {
        return blogCategoryDao.listBlogCategoryByPage(map);
    }

    @Override
    public Integer countBlogCategory(Map<String, Object> map) {
        return blogCategoryDao.countBlogCategory(map);
    }

    @Override
    public int saveBlogCategory(BlogCategory blogCategory) {
        return blogCategoryDao.saveBlogCategory(blogCategory);
    }

    @Override
    public int updateBlogCategory(BlogCategory blogCategory) {
        return blogCategoryDao.updateBlogCategory(blogCategory);
    }

    @Override
    public void deleteBlogCategory(int id) {
        blogCategoryDao.deleteBlogCategory(id);
    }
}
