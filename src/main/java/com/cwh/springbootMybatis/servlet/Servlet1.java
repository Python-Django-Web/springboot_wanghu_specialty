package com.cwh.springbootMybatis.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用注解注册Servlet
 * @WebServlet(urlPatterns="/servlet/servlet1", description="通过注解注册") 
 * 不指定name的情况下，name默认值为类全路径，即org.springboot.sample.servlet.Servlet1
 * @author wanghu
 */
//不指定name的情况下，name默认值为类全路径，即com.example.servlet.Servlet2

@WebServlet(urlPatterns="/servlet/servlet1.action",description="通过注解注册")
public class Servlet1 extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Servlet初始化...");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("-------->doGet()");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("---------->doPost()");
		resp.setContentType("text/html");
		String id = req.getParameter("id");
		if(id != null && !id.equals("")){
			resp.getWriter().append("----------> doPost() id= "+id);
			return;
		}
		resp.getWriter().append("----------> doPost()");
	}
	
	
	
}
