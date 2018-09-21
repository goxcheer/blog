package com.qgx.dao;

import com.qgx.entity.BlogCategory;

import java.util.List;
import java.util.Map;

/**
 *@Author: goxcheer
 *@Date:9:49 2018/8/5
 *@email:604721660@qq.com
 *@decription: 博客类别Dao接口
 */
public interface BlogCategoryDao {

    /**
     *查询所有的博客类别
     * @return
     */
    public List<BlogCategory> listBlogCategory();

    /**
     * 根据id获取博客分类信息
     * @param id
     * @return
     */
    public BlogCategory getBlogCategoryById(Integer id);

    /**
     * 带条件查询所有博客分类
     * @param map
     * @return
     */
    List<BlogCategory> listBlogCategoryByPage(Map<String, Object> map);

    /**
     * 带条件查询博客类别数
     * @param map
     * @return
     */
    Integer countBlogCategory(Map<String, Object> map);

    /**
     * 添加博客分类信息
     * @param blogCategory
     * @return
     */
    int saveBlogCategory(BlogCategory blogCategory);

    /**
     * 更新博客分类信息
     * @param blogCategory
     * @return
     */
    int updateBlogCategory(BlogCategory blogCategory);

    /**
     * 删除博客分类
     * @param id
     */
    void deleteBlogCategory(int id);
}
