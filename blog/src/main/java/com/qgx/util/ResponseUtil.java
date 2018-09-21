package com.qgx.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 *@Author: goxcheer
 *@Date:22:19 2018/9/4
 *@email:604721660@qq.com
 *@decription: 向页面返回数据工具类
 */
public final class ResponseUtil {

	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
