package com.qgx.web.controller;

import com.qgx.entity.Comment;
import com.qgx.service.CommentService;
import com.qgx.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@Author: goxcheer
 *@Date:10:52 2018/9/17
 *@email:604721660@qq.com
 *@decription:评论Controller层
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 客户端添加博客
     * @param checkCode
     * @param comment
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/add")
    public void addComment(@RequestParam("checkCode")String checkCode, Comment comment, HttpServletRequest request, HttpServletResponse response) throws  Exception {
        JSONObject result = new JSONObject();
        String session_checkCode = (String)request.getSession().getAttribute("sRand");
        if (!checkCode.equals(session_checkCode)) {
            result.put("success", false);
            result.put("errorInfo", "验证码填写有误啊老铁！");
        } else {
            comment.setUserIp(request.getRemoteAddr());
            Integer resultTotal = commentService.saveComment(comment);
            if (resultTotal >0) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        }
        ResponseUtil.write(response, result);
    }


}
