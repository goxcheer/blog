package com.qgx.web.controller;

import com.qgx.entity.Blog;
import com.qgx.entity.BlogCategory;
import com.qgx.entity.Blogger;
import com.qgx.service.BlogCategoryService;
import com.qgx.service.BlogService;
import com.qgx.service.BloggerService;
import com.qgx.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: goxcheer
 * @Date:14:51 2018/8/5
 * @email:604721660@qq.com
 * @decription: 首页展示Controller层
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private BlogService blogService;
    @Resource
    private BloggerService bloggerService;
    @Resource
    private BlogCategoryService blogCategoryService;


    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "page", required = false) String page,
                              @RequestParam(value = "categoryId", required = false) String categoryId,
                              @RequestParam(value = "releaseDate", required = false) String releaseDate,
                              HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        RedisUtil redisUtil = (RedisUtil) SpringContextUtil.getBean("redisUtil");
        //获取博主信息
        Blogger blogger = (Blogger)redisUtil.get("blogger");
        if (blogger == null) {
            blogger = bloggerService.getBlogger();
            redisUtil.set("blogger", blogger);
        }
        mav.addObject("blogger",blogger);
        //获取博客分类信息
        List<BlogCategory> blogCategoryList = (List<BlogCategory>)redisUtil.get("blogCategoryList");
        if (blogCategoryList == null || blogCategoryList.size() ==0) {
            blogCategoryList = blogCategoryService.listBlogCategory();
            redisUtil.set("blogCategoryList", blogCategoryList);
        }
        mav.addObject("blogCategoryList",blogCategoryList);
        //获取，时间分类
        List<Blog> dateCategoryList = (List<Blog>)redisUtil.get("dateCategoryList");
        if (dateCategoryList == null || dateCategoryList.size() == 0) {
            dateCategoryList = blogService.listDateCategory();
            redisUtil.set("dateCategoryList", dateCategoryList);
        }
        mav.addObject("dateCategoryList",dateCategoryList);
        //获取博客阅读排行
        List<Blog> blogListDesc = blogService.listBlogByClickCount();
        mav.addObject("blogListDesc",blogListDesc);

        if(StringUtil.isEmpty(page))

        {   //起始页为1
            page = "1";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        Map<String, Object> map = new HashMap<>();  //参数map
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        map.put("categoryId",categoryId);
        map.put("releaseDateStr",releaseDate);
        List<Blog> blogList = blogService.listBlog(map);
        for(Blog blog :blogList) {
            List<String> imgList = blog.getImgList();
            String blogInfo = blog.getContent();
            Document doc = Jsoup.parse(blogInfo);
            Elements jpgs = doc.select("img[src$=.jpg]");
            if (jpgs != null && jpgs.size() > 0) {
                Element jpg = jpgs.get(0);
                imgList.add(jpg.toString());
            }
         }
        mav.addObject("blogList",blogList);
        StringBuffer param = new StringBuffer();
        if(StringUtil.isNotEmpty(categoryId)) {
        param.append("categoryId=" + categoryId);
        }
        if(StringUtil.isNotEmpty(releaseDate))
        {
        param.append("releaseDate=" + releaseDate);
        }
        mav.addObject("pageCode",PageUtil.genPagination(request.getContextPath()+"/index.html",blogService.countBlog(map),pageBean.getPage(),pageBean.getPageSize(),param.toString()));
        mav.addObject("pageTitle","goxcheer个人小栈");
        mav.setViewName("mainTemp");
        return mav;
}

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.2.132", 6379);
        jedis.auth("123456");
        jedis.set("name", "goxcheer");
        System.out.println(jedis.get("name"));
        jedis.close();
    }
}
