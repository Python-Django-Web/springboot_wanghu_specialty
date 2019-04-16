package com.cwh.springbootMybatis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
/**
 * 接口数据返回工具类
 * @author wanghu
 *
 */
public class JsonUtil {

	protected Integer pagesize_1 = 5;


	public String getOppen_id(HttpSession session) {
		return (String) session.getAttribute("oppen_id");
	}
	public void setOppen_id(String oppen_id, HttpSession session){
		session.setAttribute("oppen_id", oppen_id);
	}
	public Integer getPagesize_1() {
		return pagesize_1;
	}

	public void setPagesize_1(Integer pagesize_1) {
		this.pagesize_1 = pagesize_1;
	}
	
	public static String data(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	Gson gson = new Gson();
	@ModelAttribute
	public void aaa(HttpServletResponse response){
		this.response = response;
	}
	
	@ModelAttribute
	public void bbb(HttpServletRequest request){
		this.request = request;
	}
	
	public void writeAlert(String msg){
		writeScript("alert('"+msg+"');");
	}
	
	public void writeScript(String msg){
		writeString("<script>"+msg+"</script>");
	}
	
	public void writeJson(Object object) {
		writeString(gson.toJson(object));
		
	}
	
	public void writeString(String str) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(str);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
//	public String getAdd_time() {
//		return add_time;
//	}
//
//	public void setAdd_time(String add_time) {
//		this.add_time = add_time;
//	}

}
