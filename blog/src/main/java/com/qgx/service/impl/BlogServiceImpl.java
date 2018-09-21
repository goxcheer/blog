package com.qgx.service.impl;

import com.qgx.dao.BlogDao;
import com.qgx.entity.Blog;
import com.qgx.lucene.BlogIndex;
import com.qgx.service.BlogService;
import com.qgx.util.RedisUtil;
import com.qgx.util.SpringContextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *@Author: goxcheer
 *@Date:11:04 2018/8/5
 *@email:604721660@qq.com
 *@decription: 博客Service实现类
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;

    BlogIndex blogIndex = new BlogIndex();

    @Override
    public List<Blog> listDateCategory() {
        return blogDao.listDateCategory();
    }

    @Override
    public List<Blog> listBlog(Map<String, Object> map) {
        return blogDao.listBlog(map);
    }

    @Override
    public Integer countBlog(Map<String, Object> map) {
        return blogDao.countBlog(map);
    }

    @Override
    public Blog getBlogById(Integer id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public Integer updateBlog(Blog blog) {
        return blogDao.updateBlog(blog);
    }

    @Override
    public Blog getLastBlogById(Integer id) {
        return blogDao.getLastBlogById(id);
    }

    @Override
    public Blog getNextBlogById(Integer id) {
        return blogDao.getNextBlogById(id);
    }

    @Override
    public int saveBlog(Blog blog) {
        return blogDao.saveBlog(blog);
    }

    @Override
    public List<Blog> listBlogByClickCount() {
        return blogDao.listBlogByClickCount();
    }

    @Override
    public List<Blog> ListIndexBlog(String keyboard) {
        return blogIndex.searchBlog(keyboard);
    }

    @Override
    public void deleteBlog(int id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public Integer updatePraise(Integer id) {
        return blogDao.updatePraise(id);
    }
}
