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
 * 微信菜单-个人中心
 * @author wanghu
 *
 */
@Controller
@RequestMapping("/web/")
public class CenterController extends SessionOpenId{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 实现model跳转页面
	 * @param model
	 * @param response
	 * @param session
	 * @return String
	 */
	@RequestMapping("/Center")
	public String MyCenter(Model model,HttpServletResponse response,HttpSession session){
			String openId = getOppen_id(session);
			System.out.println(openId);
		return "MyCenter";
	}
	
}
