package com.cwh.springbootMybatis.web.wx.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cwh.springbootMybatis.util.SessionOpenId;
import com.cwh.springbootMybatis.web.wx.user.entity.User;
import com.cwh.springbootMybatis.web.wx.user.service.UserService;
import com.cwh.springbootMybatis.weixin.util.CommonUtil;
import com.cwh.springbootMybatis.weixin.util.WxUtil;


/**
 * 微信公众号获取用户信
 * @author 王虎
 *
 */
@RestController
@RequestMapping(value = "/Web/User/")
public class UserController extends SessionOpenId{

	//日志
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	Map<String, Object> map = new HashMap();
	private User user = new User();
	
	@Autowired 
	private UserService UserService;
	/**
	 * 
	 * @param url
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/insert")
	public ModelAndView insert(String url, HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		String openid = "";
		String set_time = sf.format(new Date());
		int isUrl = 0;
		if (session.getAttribute("openid") == null) {
			this.map = WxUtil.oppenIdInfo(request, session);
			openid = (String) this.map.get("openid");
			if (StringUtils.isNotEmpty(openid)) {
				this.user.setOpenid((openid));
				this.user.setUsername((String) this.map.get("realname"));
				this.user.setUserhead((String) this.map.get("head_img"));
				this.user.setRegtime(set_time);
				
				session.setAttribute("openid", openid);
				if (this.UserService.selectUserByOpenId(openid) != null) {
					isUrl = this.UserService.updataUser(user);
				} else {
					isUrl = this.UserService.insertUser(this.user);
				}
			} else {
				isUrl = -1;
			}
		} else {
			isUrl = 1;
		}
		url = "redirect:" + url;
		String reUrl = (isUrl == 1) ? url : "page/error";
		return new ModelAndView(reUrl);
		
	}
	/**
	 * 
	 */
	@RequestMapping("/insertUser")
	public User insert(){
		User user = new User();
		user.setOpenid("123");
		user.setRealname("qwew");
		user.setUserhead("wqeqwe");
		user.setUsername("asdadas");
		user.setUserid("asda");
		Integer isUrl = UserService.insertUser(user);
		System.out.println(isUrl);
		return user;
	}
	
	@RequestMapping("selectUserByOpenId")
	public User selectUserByOpenId(){
		User user = UserService.selectUserByOpenId("1233");
		return user;
	}
	
	@RequestMapping("{openid}/update/{username}")
	public Integer update(@PathVariable("openid") String openid, @PathVariable("username") String username){
		User user = new User();
		user.setOpenid(openid);
		user.setUsername(username);
		Integer intuser = UserService.updataUser(user);
		return intuser;
	}
	
	@RequestMapping("te")
	public void asdasd(){
			CommonUtil commonUtil = new CommonUtil();
			commonUtil.getToken("wx48f1f4300263e8c4","dd1e4430b46267bb6f52a299e2094be9");
	}
}
