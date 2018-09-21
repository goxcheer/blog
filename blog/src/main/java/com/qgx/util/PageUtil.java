package com.qgx.util;

/**
 *@Author: goxcheer
 *@Date:11:44 2018/8/19
 *@email:604721660@qq.com
 *@decription: 分页显示工具类
 */
public  final class PageUtil {

	/**
	 *
	 * @param targetUrl  目标地址
	 * @param totalNum   总记录数
	 * @param currentPage  当前页
	 * @param pageSize  每页显示的记录数
	 * @param param
	 * @return
	 */
	public static String genPagination(String targetUrl,long totalNum,int currentPage,int pageSize,String param){
		long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		if(totalPage==0){
			return "未查询到数据";
		}else{
			StringBuffer pageCode=new StringBuffer();
			pageCode.append("<a href='"+targetUrl+"?page=1&"+param+"'>首页</a>&nbsp&nbsp");    //首页html
			if(currentPage>1){
				pageCode.append("<a href='"+targetUrl+"?page="+(currentPage-1)+"&"+param+"'>上一页</a>&nbsp&nbsp");
			}else{
				pageCode.append("<a href='"+targetUrl+"?page="+(currentPage-1)+"&"+param+"' onclick=\"return false\">上一页</a>&nbsp&nbsp");
			}
			for(int i=currentPage-2;i<=currentPage+2;i++){
				if(i<1||i>totalPage){
					continue;
				}
				if(i==currentPage){
					pageCode.append("<a href='"+targetUrl+"?page="+i+"&"+param+"' class='curPage'>"+i+"</a>&nbsp&nbsp");
				}else{
					pageCode.append("<a href='"+targetUrl+"?page="+i+"&"+param+"'>"+i+"</a>&nbsp&nbsp");
				}
			}
			if(currentPage<totalPage){
				pageCode.append("<a href='"+targetUrl+"?page="+(currentPage+1)+"&"+param+"'>下一页</a>&nbsp&nbsp");
			}else{
				pageCode.append("<a href='"+targetUrl+"?page="+(currentPage+1)+"&"+param+"' onclick=\"return false\">下一页</a>&nbsp&nbsp");
			}
			pageCode.append("<a href='"+targetUrl+"?page="+totalPage+"&"+param+"'>尾页</a>&nbsp&nbsp");
			return pageCode.toString();
		}
	}
	

	
	
}
