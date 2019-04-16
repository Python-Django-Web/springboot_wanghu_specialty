package com.cwh.springbootMybatis.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;

/**
 * @see 拦截器
 * @Author:wanghu
 * @Description:
 * @Date:Create in 17:02 2018/3/12
 * @Modified By:
 */
public class SessionInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//进入拦截器后再次进行拦截
	private static final String[] IGNORE_URI = {"/insertUser","/selectUserByOpenId","/update","/te","/pay","/out_trade_no","/app",
			"/sm","/wechat","/h5","/web","/kindeditor"};
		
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
    	System.out.println("开始请求地址拦截");
    	
    	HttpSession session = request.getSession(false);
        // SessionOpenId sessionOpenid = new SessionOpenId();
        /// String openId = sessionOpenid.getOppen_id(session);
     	//System.out.println("SessionInterceptor.java oppen_id="
 		//		+ sessionOpenid.getOppen_id(session));
         //logger.info("拦截器获取，openid:",openId);
     	
         boolean flag = false;
         //flag = (openId != null ? true : false);
         
         String url = (request.getRequestURL() + "?" + request.getQueryString())
 				.toString();
         System.out.println(">>>1:" + url);
         this.logger.info("请求,url{}:",url);
         
         for (String s : IGNORE_URI) {
 			if (url.contains(s)) {
 				flag = true;
 				break;
 			}
 		}
         
         this.logger.info("url>>>2: " + url);
 		//
 		if (!flag) {
 			/*Object ctx = new ClassPathXmlApplicationContext(
 					new String[] { "classpath:applicationContext.xml" });
 			WxSettingServiceImpl wxSettingService = (WxSettingServiceImpl) ((AbstractApplicationContext) ctx)
 					.getBean("wxSettingServiceImpl");
 			WxSetting wxSetting = wxSettingService.selectByPrimaryKey(Integer
 					.valueOf(1));
 			if ((request.getHeader("x-requested-with") != null)
 					&& (request.getHeader("x-requested-with")
 							.equalsIgnoreCase("XMLHttpRequest"))) {
 				this.logger.info("ajax -- 进入--->>>");
 				this.map.put("rs_code", Integer.valueOf(1005));
 				response.getWriter().write(this.gson.toJson(this.map));*/
 			} else {
 			/*	this.logger.info("不是ajax -- 进入--->>>");
 				System.out.println("wxSetting.getAppid()=="
 						+ wxSetting.getAppid());
 				response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
 						+ wxSetting.getAppid()
 						+ "&redirect_uri="
 						+ wxSetting.getLink()
 						+ "/Flynet/User/insertUser.do?url="
 						+ url
 						+ "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
 			}*/
 		}
 		this.logger.info("最终 111flag==" + flag + "进图判断获取用户信息");

 		return flag;
    	
    	
    	 /* HttpSession session = request.getSession(false);
          String url = (request.getRequestURL() + "?" + request.getQueryString())
  				.toString();
          logger.info("请求url:"+url);
          if (session != null && session.getAttribute("user") != null) {
              return true;
          } else {
              PrintWriter printWriter = response.getWriter();
              printWriter.write("{code:0,message:\"not login!\"}");
              return false;
          }*/
      }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("返回视图或String之前的处理");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("返回视图或String之前的处理");
    }
}
