package com.cwh.springbootMybatis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
/**
 * session类用来存openid
 * @author wanghu
 *
 */
public class SessionOpenId {
	
	protected HttpServletResponse response;
	protected HttpServletRequest request;

	public String getOppen_id(HttpSession session) {
		return (String) session.getAttribute("oppen_id");
	}

	public void setOppen_id(String oppen_id, HttpSession session){
		session.setAttribute("oppen_id", oppen_id);
	}

	@ModelAttribute
	public void aaa(HttpServletResponse response){
		this.response = response;
	}
	
	@ModelAttribute
	public void bbb(HttpServletRequest request){
		this.request = request;
	}
	
	public static String data(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	
}
