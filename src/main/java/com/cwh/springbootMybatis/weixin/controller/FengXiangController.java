package com.cwh.springbootMybatis.weixin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.weixin.util.JsapiTicketUtil;
import com.cwh.springbootMybatis.weixin.util.SignUtil;

@RestController
@RequestMapping
public class FengXiangController {

	GeneralReturn Gr;
	
	@RequestMapping("share")
	public void share(String url) throws Exception{
		 Map<String, String> ret = new HashMap<String, String>();
	        String jsapi_ticket = JsapiTicketUtil.getJsapiTicket();
	        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
	        String nonceStr = UUID.randomUUID().toString();
	        String signature = SignUtil.getSignature(
	            jsapi_ticket, nonceStr, timestamp,
	            url);
	        ret.put("url", url);
	        ret.put("jsapi_ticket", jsapi_ticket);
	        ret.put("nonceStr", nonceStr);
	        ret.put("timestamp", timestamp);
	        ret.put("signature", signature);
	        ret.put("appid", "wxb817738ae4b55bcf");
	      // ret.put("appid", "wxb817738ae4b55bcf");
	        System.out.println("分享你二维码的页面"+ret);
	}
	
	
}
