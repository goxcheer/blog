package com.qgx.dao;

import com.qgx.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * @Author: goxcheer
 * @Date:10:35 2018/8/5
 * @email:604721660@qq.com
 * @decription: 博客Dao接口
 */
public interface BlogDao {
    /**
     * 查询含有日期字符串的
     *
     * @return
     */
    List<Blog> listDateCategory();

    /**
     * 条件分页查询博客
     *
     * @param map
     * @return
     */
    List<Blog> listBlog(Map<String, Object> map);

    /**
     * 条件查询博客总数
     *
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
     * 查询浏览次数最多的博客
     * @return
     */
    List<Blog> listBlogByClickCount();

    /**
     * 删除博客
     * @param id
     */
    void deleteBlog(int id);

    /**
     * 对博客点赞
     * @param id
     * @return
     */
    Integer updatePraise(Integer id);
}
