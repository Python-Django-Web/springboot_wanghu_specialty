package com.cwh.springbootMybatis.web.cMenuUrl;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwh.springbootMybatis.util.SessionOpenId;

/**
 * 微信菜单 - 首页商品页
 * 
 * @author wanghu
 *
 */
@Controller
@RequestMapping("/web/")
public class LoginController extends SessionOpenId{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 实现model跳转页面
	 * @param model
	 * @param response
	 * @param session
	 * @return String
	 */
	@RequestMapping("/login")
	public String Login(Model model,HttpServletResponse response,HttpSession session){
		//获取openid
		String openid = getOppen_id(session);
		System.out.println(openid);
		return "login";
	}
}
