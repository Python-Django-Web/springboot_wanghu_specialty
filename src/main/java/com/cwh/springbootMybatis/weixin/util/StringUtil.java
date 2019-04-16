package com.cwh.springbootMybatis.weixin.util;

import com.cwh.springbootMybatis.tool.ContextUtils;
import com.cwh.springbootMybatis.weixin.entity.WxSetting;
import com.cwh.springbootMybatis.weixin.service.WxSettingService;

/**
 * 微信公众号查询基本配置工具父类
 * @author wanghu
 *
 */
public class StringUtil {
	public WxSetting getSetting(){
		//AbstractApplicationContext ctx   = new ClassPathXmlApplicationContext(new String []{"classpath:applicationContext.xml"});
		WxSettingService wxSettingService =(WxSettingService)ContextUtils.getBean("wxSettingServiceImpl") ;
		return wxSettingService.selectByPrimaryKey(1);
	}
	
	static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";//创建菜单
	static String token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code"; // 网页授权获取用户信息接口
	static String token_url2 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";// 全局accesstoken接口
	static String template_id_1 = "C6YRjqsvDLYFuVQbVHfAWKWPbXD8Ca_lSwiXG8cQQNY"; // 订单支付成功信息推送模板
	static String template_id_2 = "tjqPjlrB1vbXatR7_HhEefzjG1UNbacVTotD85J_ZR8	"; // 商品已发出通知
}
