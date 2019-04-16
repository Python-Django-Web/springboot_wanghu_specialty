package com.cwh.springbootMybatis.weixin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.weixin.util.QRcodeUtil;

/**
 * 创建永久二维码
 * @author wanghu
 *
 */
@RestController
@RequestMapping
public class QrcodeController {

	GeneralReturn GR;
	
	/**
	 * 
	 * @param device
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value ="/Qrcode/create")
	public GeneralReturn createQrcode( HttpServletResponse response,
			HttpSession session) throws Exception {
			String Qr = QRcodeUtil.createQrcode("123456");
			GR = GeneralReturn.build(1, "success", Qr);
			return GR;
	}
	
}
