package com.qgx.web.controller.admin;

import com.google.gson.Gson;
import com.qgx.entity.BlogCategory;
import com.qgx.service.BlogCategoryService;
import com.qgx.service.BlogService;
import com.qgx.util.PageBean;
import com.qgx.util.ResponseUtil;
import net.sf.json.JSONArray;
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
 * @Date:18:53 2018/9/15
 * @email:604721660@qq.com
 * @decription: 博客分类管理员Controller
 */
@Controller
@RequestMapping("/admin/blogCategory")
public class BlogCateogryAdminController {

    @Resource
    private BlogCategoryService blogCategoryService;

    @Resource
    private BlogService blogService;

    /**
     * 查询博客分类集合
     *
     * @param page
     * @param rows
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<BlogCategory> blogTypeList = blogCategoryService.listBlogCategoryByPage(map);
        Integer total = blogCategoryService.countBlogCategory(map);
        Gson gson =new Gson();
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 添加或者修改博客类别信息
     * @param blogCategory
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public String save(BlogCategory blogCategory,HttpServletResponse response)throws Exception{
        int resultTotal=0;
        if(blogCategory.getId()==null){
            resultTotal=blogCategoryService.saveBlogCategory(blogCategory);
        }else{
            resultTotal=blogCategoryService.updateBlogCategory(blogCategory);
        }
        JSONObject result=new JSONObject();
        if(resultTotal>0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 博客类别信息删除
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
        String []idsStr=ids.split(",");
        JSONObject result=new JSONObject();
        for(int i=0;i<idsStr.length;i++){
                blogCategoryService.deleteBlogCategory(Integer.parseInt(idsStr[i]));
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
