package com.qgx.service;

import com.qgx.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 *@Author: goxcheer
 *@Date:11:03 2018/8/5
 *@email:604721660@qq.com
 *@decription: 博客Service接口
 */
public interface BlogService {
    /**
     * 根据日期查询分类
     * @return
     */
    public List<Blog> listDateCategory();

     /**
     *分页条件查询博客信息
     * @param map
     * @return
     */
    List<Blog> listBlog(Map<String, Object> map);

    /**
     * 条件查询博客总数
     * @param map
     * @return
     */
    Integer countBlog(Map<String, Object> map);

    /**
     * 根据id获取博客信息
     * @param id
     * @return
     */
    Blog getBlogById(Integer id);

    /**
     * 更新博客信息
     * @param blog
     */
    Integer updateBlog(Blog blog);

    /**
     * 获取上一篇博客
     * @param id
     * @return
     */
    Blog getLastBlogById(Integer id);

    /**
     * 获取下一篇博客
     * @param id
     * @return
     */
    Blog getNextBlogById(Integer id);

    /**
     * 添加博客
     * @param blog
     * @return
     */
    int saveBlog(Blog blog);

    /**
     * 根据博客点击次数查询博客集合
     * @return
     */
    List<Blog> listBlogByClickCount();

    /**
     * lucene关键字索引查询博客集合
     * @param keyboard
     * @return
     */
    List<Blog> ListIndexBlog(String keyboard);

    /**
     * 删除博客
     * @param parseInt
     */
    void deleteBlog(int parseInt);

    /**
     * 对博客点赞
     * @param id
     * @return
     */
    Integer updatePraise(Integer id);
}
