package com.cwh.springbootMybatis.web.cMenuUrl;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/web/")
public class test {

	
	@RequestMapping("/text")
	public String t(Model model,HttpServletResponse response,HttpSession session){
		
		return "text";
	}
	
}
