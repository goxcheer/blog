package com.qgx.web.controller;

import com.qgx.entity.Blogger;
import com.qgx.service.BloggerService;
import com.qgx.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *@Author: goxcheer
 *@Date:13:49 2018/7/28 
 *@email:604721660@qq.com 
 *@decription: 博主Controller层
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

    @Resource
    private BloggerService bloggerService;

    @RequestMapping("/login")
    public String login(Blogger blogger, HttpServletRequest request) {
            Subject subject = SecurityUtils.getSubject();
          UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUsername(), StringUtil.MD5Encrypt(blogger.getPassword(), "goxcheer") );
          try {
              subject.login(token); //登陆认证
              return "redirect:/admin/main.jsp";
          }catch (Exception e) {
              e.printStackTrace();
              request.setAttribute("blogger", blogger);
              request.setAttribute("errorMsg", "用户名或密码错误");
              return "login";
          }
    }
}
