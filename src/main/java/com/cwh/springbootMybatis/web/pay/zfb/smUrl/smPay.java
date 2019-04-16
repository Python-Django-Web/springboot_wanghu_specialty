package com.cwh.springbootMybatis.web.pay.zfb.smUrl;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.cwh.springbootMybatis.web.pay.zfb.util.AlipayConfig;
import com.cwh.springbootMybatis.web.pay.zfb.util.DateUtil;


/**
 * 支付宝扫码url支付
 * 
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/sm/")
public class smPay{

	// 可将参数配置到properties文件中
	// private static final Prop prop = PropKit.use("alipay.properties");

	public static AlipayClient alipayClient;
	/*
	 * public static String charset = "UTF-8"; public static String privateKey =
	 * "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA4n2Tr5hFQ2skkwITnvXXsPcNbyA9GD86adX2CBKbaeWCgFKzlqOAKKcyHrThnotoxocY370Egm2/WRLLcJnTESGBKOmQG6RH7qJP6Li1FRuA/sJJABCnYThthbQseW+8vcE8bg5yuqGcWXOVpD5nAPU1LVe8D+hGp9Zt3AWcjdTOcTyHEXx7LB/ljRt+3qApJeOC6h6weMV7d4LIbiUl34CYBtjEntDUPuK+Vy6v8cS7J+G60jR0x3Iz2XZfCYVMKVsSOWAKKcd5o/9Vq6OSUiR9qwwFtuyMC9zqyWBaGPEUgsT3dGvMPBRTpqIh6u5BgPxTAzqmK+6uboV4ozPNAgMBAAECggEAItSSmUnX/cfleaHc6s6Ho72oVlIUxU37MF8Puoid9rAcnTLupqfsEaNq48mWmm6NQEh+lANLAp/45sA8qwwIFhlIE66pyA8gS2w7IxtCZ3WH1EmE2dORrJpCdJ6wfWBoCPrC8QDRGMJFEIrACM21K4L/wVo6npEx9X5AHbpbLd5oRK/WMAom5vWBhjxCysGW1anB2NnIiiH4GjeAY9q5E9Fe8O3f7doBlfwAqs3ZGoQH4ByM9p0wj/G0es/1ZwPTHTu+kgiulKMAirYqPgMY8j4Vk2hkaZoeTEq/hUZuPI8mifkFIs4oSv8yTX/QSLvmEXbw7hT997MBRFGLdBZ5YQKBgQC9PDgQouMaRlGQpxlUKxGcRpT/n5HxjDjjGFQBsLZFHd5nUKeNrsJ/KHUh1BF1VJKdnre7XYh0Me4wqlgtlWaOD+C9hx4sczq5WQs6MO/Tm7YQKBYmVtEu48UFvNl9IawU8zggKdNGF8FIsb1ae8s/EOwqFunH2eOShgDhpCrNKQKBgQCuW2WMRjv1AK6rWMqEuMIx3/wMBNNv2XD0CIQX8myvU7HoOyWFg3ugwCj/eeH9oj05D67oyWdsUrcyEEVApndMJ1O0ZhmAYXHKrFwMW3Pjbaehi688SxycXPgcV8hvNdx5eksEm9vudV16USdHCK9ZMigehjF4gwsLnSn0LBfiBQKBgDLVa2Ykq4cSXrTR/n7atvdZCouEjQrrFRkEHwT5OG1msajlRK1NxdeswZJdynbPSjdnPInrfU1hYE/YYqmUGaGN6FxhYJymM584qUhvfu1EResfKZwQ9E55RyLccTwpAHX8RBZ3xdZtaTGi9W/KE3YUkKxZzWEvSe/BaZkzpX/JAoGBAJE3nkRO2vEcOF6N/V69Y0CXBR4dhtzJhcI6tYocZ1k/pBV/7ahd8kHYyky9/vN4hPVZkpxf4rvdyKbx1jwaaZILzzOZNfQvNM9Q7vl70fHCCrQJ21GkXZf99sfkdRPrEE49BO45wdvB9LFDlisbmK/5jmCd8+YjfreVlrO/ZcThAoGAOew96D9dz1xTxn3vqaq6ue9sqBveXwOB51ANQ1g6FcLVHBj7UR2aV9SImg/SueFRa5EodEoVBu9J15vx45NpP1XLpRTKkmd+Gh0WDJAtyvNzsKuD7pd8CZlk1zDXq/YQRRWPGOeLLhtFcWWBJcpeAWHxtCresQ0OW02KLoCx3EE="
	 * ; public static String alipayPulicKey =
	 * "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAieg/Lv81A+0fEVCJtbzrxP91UFG9coalCd2O1f87Sdwug6i4uTKbTQA/nArVmpX6ZYU/erp5Bv6P/UfF/YnL4XjO/us0Qt4j9S50lETi3+tg0USOewDNPU0QL7YXSzR2tOarcZwujjUVwepUcKUtVd+gMF2NVP3q/4uiIJ4pEqXAMpF+Rk6dMWi7HiBq3K4JwOQXEOi1xNndr902GJFhkBPSLKs4fbIhBZw6e3TD3gkTi/Bp0+zASqoomsANwkGiYnBJxnM20Tn18AF2pIx8vvt//jKk3T5NW9fvswGcTKNJ8t9nx4+3KkNb2JciVW349F0sKdg2GMfjBGMQ/e3LzwIDAQAB"
	 * ; public static String serverUrl =
	 * "https://openapi.alipay.com/gateway.do"; // 支付网关 public static String
	 * appId = "2017080508047920"; public static String format = "json"; public
	 * static String signType = "RSA2"; public static String notify_domain =
	 * "http://wz111.free.ngrok.cc";
	 */

	static {
		alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,
				AlipayConfig.app_id, AlipayConfig.merchant_private_key,
				AlipayConfig.format, AlipayConfig.charset,
				AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	}

	/**
	 * 扫码支付
	 * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.i0UVZn
	 * &treeId=193&articleId=105170&docType=1#s4
	 * 
	 * @param notifyUrl
	 * @return
	 * @throws AlipayApiException
	 */
	public static String tradePrecreatePay(AlipayTradePrecreateModel model,
			String notifyUrl) throws AlipayApiException {
		AlipayTradePrecreateResponse response = tradePrecreatePayToResponse(
				model, notifyUrl);
		return response.getBody();
	}

	public static AlipayTradePrecreateResponse tradePrecreatePayToResponse(
			AlipayTradePrecreateModel model, String notifyUrl)
			throws AlipayApiException {
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);

		return alipayClient.execute(request);
	}

	/**
	 * 扫码支付
	 */
	@RequestMapping("/smPay")
	public String tradePrecreatePay() {
		System.out.println("{{{{进入支付宝扫码支付接口，接口地址url: /zfbsmUrl/zfbsmUrlPay");
		//初始化参数
		String subject = "优点 ：Javen 支付宝扫码支付测试";
		String totalAmount = "0.01";
		String storeId = "123456";
		String notifyUrl = AlipayConfig.notify_url;

		AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
		model.setSubject(subject);//订单标题
		model.setTotalAmount(totalAmount);//支付金额
		model.setStoreId(storeId);//商家门店号
		model.setTimeoutExpress("5m");//二维码生效时间
		model.setOutTradeNo("wan" + DateUtil.Obtain_date("yyyyMMddHHmmssSSS"));
		
		try {
			String resultStr = tradePrecreatePay(model, notifyUrl);
			JSONObject jsonObject = JSONObject.parseObject(resultStr);
			System.out.println("支付宝返回参数：" + jsonObject);
			
			String qr_code = jsonObject.getJSONObject(
					"alipay_trade_precreate_response").getString("qr_code");
			
			System.out.println("}}}}返回支付宝二维码url："+qr_code);
			
			 return qr_code;
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 return null;
	}

	/**
	 * 扫码支付通知
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("notify_url")
	public void precreate_notify_url(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("{{{{进入支付宝异步回调地址url： /zfbsmUrl/notify_url");
		PrintWriter writer = response.getWriter();

		try {
			Map<String, String> map = toMap(request);
			System.out.println(map.toString());
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}
			boolean flag = AlipaySignature.rsaCheckV1(map,
					AlipayConfig.alipay_public_key, AlipayConfig.charset,
					AlipayConfig.sign_type);
			System.out.println("}}}}支付宝返回参数：" + flag);
			if (flag) {
				// TODO写项目业务代码
				// TODO写项目业务代码
				System.out.println("precreate_notify_url success");
				writer.println("success");
				return;
			} else {
				// TODO写项目业务代码
				// TODO写项目业务代码
				System.out.println("precreate_notify_url failure");
				writer.println("failure");
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
			writer.println("failure");
		}

	}

	/**
	 * 将异步通知的参数转化为Map
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> toMap(HttpServletRequest request)
			throws UnsupportedEncodingException {
		System.out.println(">>>>" + request.getRequestURI());
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		return params;
	}
}
