package com.qgx.service;

import com.qgx.entity.BlogCategory;

import java.util.List;
import java.util.Map;

/**
 *@Author: goxcheer
 *@Date:10:04 2018/8/5
 *@email:604721660@qq.com
 *@decription: 博客类别Service接口
 */
public interface BlogCategoryService {

    /**
     *查询所有的博客类别
     * @return
     */
    public List<BlogCategory> listBlogCategory();

    /**
     * 带条件查询所有博客信息
     * @param map
     * @return
     */
    List<BlogCategory> listBlogCategoryByPage(Map<String, Object> map);

    /**
     * 带条件查询博客分类数
     * @param map
     * @return
     */
    Integer countBlogCategory(Map<String, Object> map);

    /**
     * 添加博客类别
     * @param blogCategory
     * @return
     */
    int saveBlogCategory(BlogCategory blogCategory);

    /**
     * 更新博客类别
     * @param blogCategory
     * @return
     */
    int updateBlogCategory(BlogCategory blogCategory);

    /**
     * 删除博客
     * @param id
     */
    void deleteBlogCategory(int id);
}
