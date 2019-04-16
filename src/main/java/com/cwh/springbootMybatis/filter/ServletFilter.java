package com.cwh.springbootMybatis.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/** 
 * @author wanghu
 * Servlet Filter implementation class ServletFilter
 * 注解注册过滤器：实现 javax.servlet.Filter接口 
 * filterName 是过滤器的名字
 * urlPatterns 是需要过滤的请求 ，这里只过滤servlet/* 下面的所有请求
 */
@WebFilter(filterName="ServletFilter",urlPatterns="/servlet/*")
public class ServletFilter implements Filter {

	
	public ServletFilter(){
		// TODO Auto-generated constructor stub
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("过滤器被销毁------>");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		System.out.println("过滤器正在执行...");
		arg2.doFilter(arg0, arg1);
		System.out.println("Execute cost="+(System.currentTimeMillis()-start));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("初始化过滤器。");
	}

	
	
}
