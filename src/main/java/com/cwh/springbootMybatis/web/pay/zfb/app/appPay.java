package com.cwh.springbootMybatis.web.pay.zfb.app;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.cwh.springbootMybatis.util.R;
import com.cwh.springbootMybatis.web.pay.zfb.util.AlipayConfig;
import com.cwh.springbootMybatis.web.pay.zfb.util.DateUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 支付宝APP支付
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/app/")
public class appPay  {

	private Logger logger = Logger.getLogger(appPay.class);

	public static AlipayClient alipayClient;
	/*AlipayClient alipayClient = AlipayUtils
			.getAlipayClientReal(AlipayConfig.gatewayUrl);*/
	static {
		alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,
				AlipayConfig.app_id, AlipayConfig.merchant_private_key,
				AlipayConfig.format, AlipayConfig.charset,
				AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	}

	Map<String, String> map = new HashMap<String, String>();

	/**
	 * 支付下订单
	 * 
	 * @param request
	 * @param response
	 * 
	 */
	@Transactional
	@RequestMapping(value = "/trade/createOrder", method = RequestMethod.GET)
	@ResponseBody
	public R tradeCreate() throws AlipayApiException {

		System.out.println("{{{{进入支付下单接口，接口地址url：/zfbAppPay/trade/create");

		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("");// 商品描述
		model.setSubject("优点 ：Javen 支付宝扫码支付测试");// 商品标题
		model.setOutTradeNo("wan" + DateUtil.Obtain_date("yyyyMMddHHmmssSSS"));// 商户订单号
		// model.setTotalAmount(realPay);//支付金额
		model.setTotalAmount("0.01");// 支付金额
		model.setTimeExpire("5m");

		//
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.notify_url);

		// 这里和普通的接口调用不同，使用的是sdkExecute
		AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);

		// 就是orderInfo 可以直接给客户端请求，无需再做处理。
		String result = response.getBody();
		System.out.println("}}}}返回生成支付订单参数：" + result);
		// 将支付宝返回参数返回给应用response.getBody()
		return R.ok().put("message", response.getBody());
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param tradeno
	 *            支付宝订单交易编号
	 * @param orderno
	 *            商家交易编号
	 * @param callback
	 */
	@ResponseBody
	@RequestMapping(value = "/trade/queryOrder", method = RequestMethod.POST)
	public R tradeQuery(String out_trade_no, String trade_no)
			throws AlipayApiException {
		System.out.println("{{{{进入支付宝查询订单接口，接口地址url：/zfbAppPay/trade/query");
		
		// 获取查询订单请求参数
		// 商户订单号，商户网站订单系统中唯一订单号：out_trade_no
		// 支付宝交易号：trade_no

		map.put("out_trade_no", out_trade_no);
		map.put("trade_no", trade_no);
		Object jsonObject = JSONObject.toJSON(map);
		String json = jsonObject.toString();
		System.out.println("转换成json字符串：" + json);
		// 设置请求参数
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent(json);

		// 请求
		AlipayTradeQueryResponse response = alipayClient.execute(request);
		System.out.println("}}}}获取查询订单返回参数" + response.getBody());
		if (response.isSuccess()) {
			System.out.println("吊起成功！返回状态response.isSuccess()："
					+ response.isSuccess());
		} else {
			System.out.println("吊起失败！返回状态response.isSuccess()："
					+ response.isSuccess());
		}
		// 将支付宝返回参数返回给应用response.getBody()
		
		return R.ok().put("message", response.getBody());
	}

	/**
	 * 订单退款
	 * 
	 * @param request
	 * @param response
	 * @param out_trade_no
	 *            支付宝交易订单号
	 * @param trade_no
	 *            商家交易号
	 * 
	 */
	@RequestMapping(value = "/trade/refund")
	public R tradeRefund(String out_trade_no, String trade_no,
			String refund_amount, String refund_reason, String out_request_no)
			throws AlipayApiException {
		System.out.println("{{{{进入订单退款接口，接口地址url:/zfbAppPay/trade/refund");
		// 获取退款订单请求参数
		// 商户订单号，商户网站订单系统中唯一订单号：out_trade_no
		// 支付宝交易号：trade_no
		// 需要退款的金额，该金额不能大于订单金额，必填：refund_amount
		// 退款的原因说明：refund_reason
		// 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传：out_request_no
		map.put("out_trade_no", out_trade_no);
		map.put("trade_no", trade_no);
		map.put("refund_amount", refund_amount);
		map.put("refund_reason", refund_reason);
		map.put("out_request_no", out_request_no);
		Object jsonObject = JSONObject.toJSON(map);
		String json = jsonObject.toString();
		System.out.println("转换成json字符串：" + json);

		// 设置请求参数
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent(json);

		// 请求
		// 通过alipayClient调用API，获得对应的response类
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		System.out.print("}}}}返回退款参数："+response.getBody());
		// 将支付宝返回参数返回给应用response.getBody()
		return R.ok().put("message", response.getBody());
	}

	/**
	 * 
	 * 退款查询
	 * 
	 * @param request
	 * @param response
	 * @param orderno
	 *            商家订单号
	 * @param tradeno
	 *            支付宝订单号
	 * 
	 */
	@RequestMapping(value = "/trade/refund/query")
	public R tradeRefund(String out_trade_no, String trade_no,
			String out_request_no) throws AlipayApiException {
		System.out.println("{{{{进入退款查询接口，接口地址url：/zfbAppPay/trade/refund/query");
		// 获取退款订单请求参数
		// 商户订单号，商户网站订单系统中唯一订单号：out_trade_no
		// 支付宝交易号：trade_no
		// 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号，必填:out_request_no
		map.put("out_trade_no", out_trade_no);
		map.put("trade_no", trade_no);
		map.put("out_request_no", out_request_no);
		Object jsonObj = JSONObject.toJSON(map);
		String requestJson = jsonObj.toString();

		// 设置请求参数
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent(requestJson);

		// 请求
		AlipayTradeRefundResponse response = alipayClient.execute(request);// 通过alipayClient调用API，获得对应的response类
		System.out.print("}}}}返回退款查询参数："+response.getBody());
		// 将支付宝返回参数返回给应用response.getBody()
		return R.ok().put("message", response.getBody());
	}

	/**
	 * 支付异步通知
	 * 这里没有使用下面的回调方法，走的扫码支付中的回调
	 * @return
	 * @throws IOException
	 */
//	@RequestMapping("notify_url")
//	public void precreate_notify_url(HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		System.out.println("----------------");
//		PrintWriter writer = response.getWriter();
//		try {
//			Map<String, String> map = toMap(request);
//			System.out.println(map.toString());
//			for (Map.Entry<String, String> entry : map.entrySet()) {
//				System.out.println(entry.getKey() + " = " + entry.getValue());
//			}
//			boolean flag = AlipaySignature.rsaCheckV1(map,
//					AlipayConfig.alipay_public_key, AlipayConfig.charset,
//					AlipayConfig.sign_type);
//			System.out.println("回调返回值：" + flag);
//			if (flag) {
//				// TODO写项目业务代码
//				System.out.println("precreate_notify_url success");
//				writer.println("success");
//				return;
//			} else {
//				// TODO写项目业务代码
//				System.out.println("precreate_notify_url failure");
//				writer.println("failure");
//			}
//		} catch (AlipayApiException e) {
//			e.printStackTrace();
//			writer.println("failure");
//		}
//
//	}

	/**
	 * 将异步通知的参数转化为Map
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
//	public static Map<String, String> toMap(HttpServletRequest request)
//			throws UnsupportedEncodingException {
//		System.out.println(">>>>" + request.getRequestURI());
//		Map<String, String> params = new HashMap<String, String>();
//		Map<String, String[]> requestParams = request.getParameterMap();
//		for (Iterator<String> iter = requestParams.keySet().iterator(); iter
//				.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i]
//						: valueStr + values[i] + ",";
//			}
//			// 乱码解决，这段代码在出现乱码时使用。
//			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//			params.put(name, valueStr);
//		}
//		return params;
//	}
}
