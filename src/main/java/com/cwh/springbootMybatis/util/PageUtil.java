package com.cwh.springbootMybatis.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 分页查询工具类
 * @author wanghu
 *
 */
public  class PageUtil {
	
	
	public  static Map<String,Integer> pager(int currentPage,int pageSize,int total){
		if(currentPage==0||currentPage<0){
			currentPage=1;
		}
		if(pageSize==0){
			pageSize=10;
		}
		int totalPage=0;
		if(total%pageSize==0){
			totalPage=total/pageSize;
		}else{
			totalPage=total/pageSize+1;
		}
		Map<String,Integer> map=new HashMap<String, Integer>();
		map.put("total",total);
		map.put("totalPage",totalPage);
		map.put("currentPage",currentPage);
		return  map;
		
		/*request.setAttribute("total",total);
		request.setAttribute("totalPage",totalPage);
		request.setAttribute("currentPage",currentPage);*/
	}
	public static void test(){
		System.out.println("*********************************");
	}
	public static int currentNum(int currentPage,int pageSize){
		if(currentPage==0||currentPage<0){
			currentPage=1;
		}
		return (currentPage-1)*pageSize;
	}
}
