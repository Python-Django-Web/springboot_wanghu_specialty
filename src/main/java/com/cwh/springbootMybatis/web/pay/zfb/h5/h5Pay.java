package com.cwh.springbootMybatis.web.pay.zfb.h5;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.cwh.springbootMybatis.web.pay.zfb.util.AlipayConfig;
import com.cwh.springbootMybatis.web.pay.zfb.util.DateUtil;

@Controller
@RequestMapping("/h5/")
public class h5Pay {

	public static AlipayClient alipayClient;
	
	static {
		alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,
				AlipayConfig.app_id, AlipayConfig.merchant_private_key,
				AlipayConfig.format, AlipayConfig.charset,
				AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	}
	
	@RequestMapping("/h5Pay")
	public String Pay(String money ,String orderId,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String total_amount="0.1";
        //自定义参数，可空
        String passback_params = "132654878996422";
		 // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no ="wan" + DateUtil.Obtain_date("yyyyMMddHHmmssSSS");
        // 商品描述，可空
        String body = "订单支付";
        // 订单名称，必填
        String subject = "购乐网商品支付";
        // 超时时间 可空
       String timeout_express="2m";
        // 销售产品码 必填
        String product_code="QUICK_WAP_WAY";
        
     // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
        //调用RSA签名方式
       // AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
        
        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        model.setPassbackParams(URLEncoder.encode(passback_params,"UTF-8"));
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.notify_url);
        // 设置同步地址
        alipay_request.setReturnUrl(AlipayConfig.return_url);
     // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(alipay_request).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.charset); 
            response.getWriter().write(form);//直接将完整的表单html输出到页面 
            response.getWriter().flush(); 
            response.getWriter().close();
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return null;
    }

	
}
