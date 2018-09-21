package com.qgx.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.qgx.entity.Blog;
import com.qgx.entity.BlogCategory;
import com.qgx.entity.Blogger;
import com.qgx.entity.Comment;
import com.qgx.service.BlogCategoryService;
import com.qgx.service.BlogService;
import com.qgx.service.BloggerService;
import com.qgx.service.CommentService;
import com.qgx.util.RedisUtil;
import com.qgx.util.ResponseUtil;
import com.qgx.util.SpringContextUtil;
import com.qgx.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.SunHints;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author: goxcheer
 *@Date:22:33 2018/8/19
 *@email:604721660@qq.com
 *@decription: 博客Controller层
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;
    @Resource
    private CommentService commentService;
    @Resource
    private BlogCategoryService blogCategoryService;
    @Resource
    private BloggerService bloggerService;


    /**
     * 请求博客的详细信息
     * @param id
     * @return
     */
    @RequestMapping("/articles/{id}")
    public ModelAndView BlogDetail(@PathVariable("id")Integer id, HttpServletRequest request) {
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
        if (blogCategoryList == null || blogCategoryList.size() == 0) {
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
        //博客排行，这个经常会变，直接从数据库中查询
        List<Blog> blogListDesc = blogService.listBlogByClickCount();
        mav.addObject("blogListDesc", blogListDesc);
        //获取博客下审核通过的评论
        List<Comment> commentList = (List<Comment>)redisUtil.get("commentList"+id);
        if (commentList == null) {
            Map<String,Object>commentParams = new HashMap<>();
            commentParams.put("blogId", id);
            commentParams.put("state", 1);
            commentList = commentService.listComment(commentParams);
            redisUtil.set("commentList"+id, commentList);
        }
        mav.addObject("commentList", commentList);
        //博客详细信息开始
        Blog blog = blogService.getBlogById(id);
        blog.setClickCount(blog.getClickCount()+1);
        blogService.updateBlog(blog);

        String keyWords = blog.getKeyWord();
        if (StringUtil.isNotEmpty(keyWords)) {
            String arr[] = keyWords.split(" ");
            List<String> keyWordsList = StringUtil.filterWhite(Arrays.asList(arr));
            mav.addObject("keyWordsList", keyWordsList);
        } else {
            mav.addObject("keyWordsList", null);
        }
        mav.addObject("lastAndNextBlogCode", getLastAndNextBlogPage(blogService.getLastBlogById(id), blogService.getNextBlogById(id), request.getContextPath()));
        mav.addObject("blog", blog);
        mav.addObject("pageTitle", blog.getTitle()+"java开源博客系统");
        mav.addObject("mainPage", "foreground/blog/view.jsp");
        mav.setViewName("info");
        return mav;
    }

    @RequestMapping("/search")
    public ModelAndView searchBlogs (@RequestParam(value="keyboard",required =false)String keyboard , @RequestParam(value = "page",required = false)String page, HttpServletRequest request) {

        int pageSize=3;
        if(StringUtil.isEmpty(page)){
            page="1";
        }
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
        if (blogCategoryList == null || blogCategoryList.size() == 0) {
            blogCategoryList = blogCategoryService.listBlogCategory();
            redisUtil.set("blogCategoryList", blogCategoryList);
        }
        mav.addObject("blogCategoryList",blogCategoryList);
        //获取，时间分类
        List<Blog> dateCategoryList = (List<Blog>)redisUtil.get("dateCategoryList");
        if (dateCategoryList == null || blogCategoryList.size() == 0) {
            dateCategoryList = blogService.listDateCategory();
            redisUtil.set("dateCategoryList", dateCategoryList);
        }
        mav.addObject("dateCategoryList",dateCategoryList);
        //博客排行，这个经常会变，直接从数据库中查询
        List<Blog> blogListDesc = blogService.listBlogByClickCount();
        mav.addObject("blogListDesc", blogListDesc);
        List<Blog>blogList = blogService.ListIndexBlog(keyboard);
        Integer toIndex=blogList.size()>=Integer.parseInt(page)*pageSize?Integer.parseInt(page)*pageSize:blogList.size();
        mav.addObject("blogList", blogList.subList((Integer.parseInt(page)-1)*pageSize, toIndex));
        mav.addObject("pageCode",this.getUpAndDownPageCode(Integer.parseInt(page), blogList.size(),keyboard ,pageSize, request.getServletContext().getContextPath()));
        mav.addObject("keyboard", keyboard);
        mav.addObject("resultTotal", blogList.size());
        mav.setViewName("search");
        return mav;
    }

    /**
     * 博客点赞
     * @param id
     * @param response
     */
    @RequestMapping("praise")
    public void praise(@RequestParam(value = "id")Integer id, HttpServletResponse response) throws Exception{
        Integer resultCount = blogService.updatePraise(id);
        Blog updateBlog = blogService.getBlogById(id);
        JSONObject result = new JSONObject();
        if (resultCount>0) {
            result.put("success", true);
            result.put("praiseCount", updateBlog.getPraiseCount());
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
    }
    /**
     * 上下页代码
     * @param lastBlog
     * @param nextBlog
     * @param basePath
     * @return
     */
    private String getLastAndNextBlogPage (Blog lastBlog, Blog nextBlog, String basePath) {
        StringBuffer sb =  new StringBuffer();
        if (lastBlog!=null && lastBlog.getId()!=null) {
            sb.append("<p><a href='"+basePath+"/blog/articles/"+lastBlog.getId()+".html'>上一篇:&nbsp;&nbsp;"+lastBlog.getTitle()+"</a><p>");
        } else {
        }
        if (nextBlog!=null && nextBlog.getId()!=null) {
            sb.append("<p><a href='"+basePath+"/blog/articles/"+nextBlog.getId()+".html'>下一篇:&nbsp;&nbsp;"+nextBlog.getTitle()+"</a><p>");
        } else {
        }
        return sb.toString();
    }

    /**
     * lucene索引获取的上下页代码
     * @param page
     * @param totalNum
     * @param keyboard
     * @param pageSize
     * @param projectContext
     * @return
     */
    private String getUpAndDownPageCode(Integer page,Integer totalNum,String keyboard,Integer pageSize,String projectContext){
        long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode=new StringBuffer();
        if(totalPage==0){
            return "";
        }else{
            if(page>1){
                pageCode.append("<a href='"+projectContext+"/blog/search.html?page="+(page-1)+"&keyboard="+keyboard+"'>上一页</a>&nbsp&nbsp");
            }else{
                pageCode.append("<a href='"+projectContext+"/blog/search.html?page="+(page-1)+"&keyboard="+keyboard+"' onclick=\"return false\">上一页</a>&nbsp&nbsp");
            }
            if(page<totalPage){
                pageCode.append("<a href='"+projectContext+"/blog/search.html?page="+(page+1)+"&keyboard="+keyboard+"'>下一页</a>&nbsp&nbsp");
            }else{
                pageCode.append("<a href='"+projectContext+"/blog/search.html?page="+(page+1)+"&keyboard="+keyboard+"' onclick=\"return false\">下一页</a>&nbsp&nbsp");
            }
        }
        return pageCode.toString();
    }
}
