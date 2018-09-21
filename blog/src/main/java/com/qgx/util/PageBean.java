package com.qgx.util;
/**
 *@Author: goxcheer
 *@Date:14:47 2018/8/5
 *@email:604721660@qq.com
 *@decription: 分页工具类
 */
public final class PageBean {

	private int page; // 当前页数
	private int pageSize; // 每页显示的记录数
	private int start;  // 起始记录


	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return (page-1)*pageSize;
	}
	
	
}
