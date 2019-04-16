package com.cwh.springbootMybatis.web.pay.wx.app;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.cwh.springbootMybatis.util.R;
import com.cwh.springbootMybatis.web.pay.wx.bean.WeChatPayRequestBean;
import com.cwh.springbootMybatis.web.pay.wx.util.Constants;
import com.cwh.springbootMybatis.web.pay.wx.util.WechatHttpUtils;
import com.cwh.springbootMybatis.web.pay.wx.util.WechatUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 * *****************************微信支付接入 Created by IntelliJ IDEA User:jaywechen
 * Date:2017/7/29 Time:11:41
 */
@Controller
@RequestMapping(value = "/wechat")
public class WeChatPayController {

	private Logger logger = Logger.getLogger(WeChatPayController.class);
	 
	/**
	 * function1:微信支付接口返回订单信息
	 */
	@RequestMapping(value = "/trade/create")
	@ResponseBody
	public R tradeCreate(WeChatPayRequestBean bean) {
		System.out.println("-------------------------------微信支付");
		int connectTimeoutMs = 0;
		int readTimeoutMs = 0;
		String orderInfo = null;
		try {
			// 1.http client to server
			String httpRequest = WechatHttpUtils.httpRequest(
					WechatUtils.getXml(bean), connectTimeoutMs, readTimeoutMs,
					Constants.url);
			logger.info("server response info:" + httpRequest);
			// 2.read map from return info
			Map<String, String> readStringXmlOut = WechatUtils
					.readStringXmlOut(httpRequest);
			logger.info("processed  order info of map type:"
					+ readStringXmlOut.toString());
			// 3.convert orderInfo to json
			orderInfo = WechatUtils.getJson(readStringXmlOut);
			logger.info("****"
					+ orderInfo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.ok().put("orderInfo", orderInfo);
	}

	/**
	 * function2:微信支付异步通知
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/callback")
	public void asy_notify(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		String resXml = "";
		System.out.println("---------------微信支付异步回调");

		InputStream inStream;
		try {
			inStream = request.getInputStream();

			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			logger.error("微信支付----付款成功----");
			outSteam.close();
			inStream.close();
			String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
			logger.error("微信支付----result----=" + result);
			Map<String, String> map = WechatUtils.readStringXmlOut(result);

			if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
				logger.error("微信支付----返回成功");
				// prep:根据返回的商户订单号查询统一下单订单
				try {			
				} catch (Exception e) {
					e.printStackTrace();
				}
				resXml = "<xml>"
						+ "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

				logger.info("微信支付回调成功结束！");
				writer.write(resXml);
				try {
					writer.flush();
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			logger.error("支付回调发布异常：" + e);
			e.printStackTrace();
		}

	}

}
