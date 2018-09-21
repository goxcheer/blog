package com.qgx.web.controller.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qgx.entity.Blog;
import com.qgx.entity.BlogCategory;
import com.qgx.lucene.BlogIndex;
import com.qgx.service.BlogCategoryService;
import com.qgx.service.BlogService;
import com.qgx.util.*;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: goxcheer
 * @Date:20:50 2018/9/14
 * @email:604721660@qq.com
 * @decription:管理员操作博客Controller
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Resource
    private BlogCategoryService blogCategoryService;
    @Resource
    private BlogService blogService;

    private BlogIndex blogIndex = new BlogIndex();

    /**
     * 获取博客分类
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/listBlogCategory")
    public void listBlogCategory(HttpServletResponse response) throws Exception {
        List<BlogCategory> blogCategoryList = blogCategoryService.listBlogCategory();
        Gson gson = new Gson();
        ResponseUtil.write(response, gson.toJson(blogCategoryList));
    }

    /**
     * 添加或修改博客信息
     *
     * @param response
     * @param blog
     * @throws Exception
     */
    @RequestMapping("/save")
    public void save(HttpServletResponse response, Blog blog) throws Exception {
        int resultTotal = 0;
        if (blog.getId() == null) {  //添加
            resultTotal = blogService.saveBlog(blog);
            blogIndex.addIndex(blog); //添加lucene索引
        } else {
            resultTotal = blogService.updateBlog(blog);
            blogIndex.updateIndex(blog); //修改lucene索引
        }
        //每添加或修改一片博客，redis缓存中相应的信息需要做修改
        RedisUtil redisUtil = (RedisUtil) SpringContextUtil.getBean("redisUtil");
        //获取博客分类信息
        List<BlogCategory> blogCategoryList = blogCategoryService.listBlogCategory();
        redisUtil.set("blogCategoryList", blogCategoryList);
        //获取，时间分类
        List<Blog> dateCategoryList = blogService.listDateCategory();
        redisUtil.set("dateCategoryList", dateCategoryList);
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
    }

    /**
     * 分页查询博客信息
     *
     * @param page
     * @param rows
     * @param s_blog
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public void list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Blog s_blog, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", StringUtil.formatLike(s_blog.getTitle()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> blogList = blogService.listBlog(map);
        Integer total = blogService.countBlog(map);
        JSONObject result = new JSONObject();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        result.put("rows", gson.toJson(blogList));
        result.put("total", total);
        ResponseUtil.write(response, result);
    }

    /**
     * 批量删除博客
     *
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            blogService.deleteBlog(Integer.parseInt(idsStr[i]));
            //每次删除博客，redis都要重新加载信息
            blogIndex.deleteIndex(idsStr[i]);
        }
        RedisUtil redisUtil = (RedisUtil) SpringContextUtil.getBean("redisUtil");
        //获取博客分类信息
        List<BlogCategory> blogCategoryList = blogCategoryService.listBlogCategory();
        redisUtil.set("blogCategoryList", blogCategoryList);
        //获取，时间分类
        List<Blog> dateCategoryList = blogService.listDateCategory();
        redisUtil.set("dateCategoryList", dateCategoryList);
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

    /**
     * 根据id获取博客
     *
     * @param id
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/getBlogById")
    public void getBlogById(@RequestParam(value = "id") String id, HttpServletResponse response) throws Exception {
        Blog blog = blogService.getBlogById(Integer.parseInt(id));
        JSONObject result = JSONObject.fromObject(blog);
        ResponseUtil.write(response, result);
    }
}
