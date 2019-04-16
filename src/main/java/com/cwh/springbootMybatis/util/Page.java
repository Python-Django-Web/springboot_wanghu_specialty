package com.cwh.springbootMybatis.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * 分页查询实体父类
 * @author wanghu
 *
 */
public class Page {

	private int currentPage;// 当前的几页
	private int totalPage;// 共有多少页
	private int total; // 共有多少行
	private int pageSize;// 每一页有多少行，默认为10行
	private int currentNum;
	public String beginTime;
	public String endTime;
	
	
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

}