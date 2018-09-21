package com.qgx.web.controller.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qgx.entity.Comment;
import com.qgx.service.CommentService;
import com.qgx.util.PageBean;
import com.qgx.util.RedisUtil;
import com.qgx.util.ResponseUtil;
import com.qgx.util.SpringContextUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author: goxcheer
 *@Date:10:51 2018/9/17
 *@email:604721660@qq.com
 *@decription:评论管理员操作
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

    @Resource
    private CommentService commentService;

    /**
     * 分页查询评论信息
     * @param page
     * @param rows
     * @param state
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public void list(@RequestParam(value="page")String page,@RequestParam(value="rows")String rows,@RequestParam(value="state",required=false)String state,HttpServletResponse response)throws Exception{
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("state", state); // 评论状态
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Comment> commentList=commentService.listComment(map);
        Integer total=commentService.countComment(map);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONObject result = new JSONObject();
        result.put("rows", gson.toJson(commentList));
        result.put("total", total);
        ResponseUtil.write(response, result);
    }

    /**
     * 评论审核
     * @param ids
     * @param state
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/review")
    public String review(@RequestParam(value="ids")String ids,@RequestParam(value="state",required=false)Integer state,HttpServletResponse response)throws Exception{
        JSONArray jsonArray = JSONArray.fromObject(ids);
        for (int i = 0; i<jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Comment comment=new Comment();
            comment.setId(Integer.parseInt(jsonObject.getString("id")));
            comment.setState(state);
            commentService.updateComment(comment);
            if (state == 1) {
                //当审核状态为1时，相应博客下面的缓存应该刷新
                Map<String,Object> map = new HashMap<>();
                map.put("state", state);
                map.put("blogId", jsonObject.getString("blogId"));
                List<Comment> commentList = commentService.listComment(map);
                RedisUtil redisUtil = (RedisUtil) SpringContextUtil.getBean("redisUtil");
                redisUtil.set("commentList"+jsonObject.getString("blogId"), commentList);
            }
        }
        JSONObject result=new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 评论信息删除
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
        JSONArray jsonArray = JSONArray.fromObject(ids);
        for (int i = 0; i<jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            commentService.deleteComment(jsonObject.getInt("id"));
            if (jsonObject.getInt("state") == 1) {
                //当审核状态为1时，相应博客下面的缓存应该刷新
                Map<String,Object> map = new HashMap<>();
                map.put("state", jsonObject.getInt("state"));
                map.put("blogId", jsonObject.getString("blogId"));
                List<Comment> commentList = commentService.listComment(map);
                RedisUtil redisUtil = (RedisUtil) SpringContextUtil.getBean("redisUtil");
                redisUtil.set("commentList"+jsonObject.getString("blogId"), commentList);
            }
        }
        JSONObject result=new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
