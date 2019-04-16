package com.cwh.springbootMybatis.web.pay.zfb.util;

import java.io.FileWriter;
import java.io.IOException;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;



/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig{
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id="2017080508047920";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA4n2Tr5hFQ2skkwITnvXXsPcNbyA9GD86adX2CBKbaeWCgFKzlqOAKKcyHrThnotoxocY370Egm2/WRLLcJnTESGBKOmQG6RH7qJP6Li1FRuA/sJJABCnYThthbQseW+8vcE8bg5yuqGcWXOVpD5nAPU1LVe8D+hGp9Zt3AWcjdTOcTyHEXx7LB/ljRt+3qApJeOC6h6weMV7d4LIbiUl34CYBtjEntDUPuK+Vy6v8cS7J+G60jR0x3Iz2XZfCYVMKVsSOWAKKcd5o/9Vq6OSUiR9qwwFtuyMC9zqyWBaGPEUgsT3dGvMPBRTpqIh6u5BgPxTAzqmK+6uboV4ozPNAgMBAAECggEAItSSmUnX/cfleaHc6s6Ho72oVlIUxU37MF8Puoid9rAcnTLupqfsEaNq48mWmm6NQEh+lANLAp/45sA8qwwIFhlIE66pyA8gS2w7IxtCZ3WH1EmE2dORrJpCdJ6wfWBoCPrC8QDRGMJFEIrACM21K4L/wVo6npEx9X5AHbpbLd5oRK/WMAom5vWBhjxCysGW1anB2NnIiiH4GjeAY9q5E9Fe8O3f7doBlfwAqs3ZGoQH4ByM9p0wj/G0es/1ZwPTHTu+kgiulKMAirYqPgMY8j4Vk2hkaZoeTEq/hUZuPI8mifkFIs4oSv8yTX/QSLvmEXbw7hT997MBRFGLdBZ5YQKBgQC9PDgQouMaRlGQpxlUKxGcRpT/n5HxjDjjGFQBsLZFHd5nUKeNrsJ/KHUh1BF1VJKdnre7XYh0Me4wqlgtlWaOD+C9hx4sczq5WQs6MO/Tm7YQKBYmVtEu48UFvNl9IawU8zggKdNGF8FIsb1ae8s/EOwqFunH2eOShgDhpCrNKQKBgQCuW2WMRjv1AK6rWMqEuMIx3/wMBNNv2XD0CIQX8myvU7HoOyWFg3ugwCj/eeH9oj05D67oyWdsUrcyEEVApndMJ1O0ZhmAYXHKrFwMW3Pjbaehi688SxycXPgcV8hvNdx5eksEm9vudV16USdHCK9ZMigehjF4gwsLnSn0LBfiBQKBgDLVa2Ykq4cSXrTR/n7atvdZCouEjQrrFRkEHwT5OG1msajlRK1NxdeswZJdynbPSjdnPInrfU1hYE/YYqmUGaGN6FxhYJymM584qUhvfu1EResfKZwQ9E55RyLccTwpAHX8RBZ3xdZtaTGi9W/KE3YUkKxZzWEvSe/BaZkzpX/JAoGBAJE3nkRO2vEcOF6N/V69Y0CXBR4dhtzJhcI6tYocZ1k/pBV/7ahd8kHYyky9/vN4hPVZkpxf4rvdyKbx1jwaaZILzzOZNfQvNM9Q7vl70fHCCrQJ21GkXZf99sfkdRPrEE49BO45wdvB9LFDlisbmK/5jmCd8+YjfreVlrO/ZcThAoGAOew96D9dz1xTxn3vqaq6ue9sqBveXwOB51ANQ1g6FcLVHBj7UR2aV9SImg/SueFRa5EodEoVBu9J15vx45NpP1XLpRTKkmd+Gh0WDJAtyvNzsKuD7pd8CZlk1zDXq/YQRRWPGOeLLhtFcWWBJcpeAWHxtCresQ0OW02KLoCx3EE=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAieg/Lv81A+0fEVCJtbzrxP91UFG9coalCd2O1f87Sdwug6i4uTKbTQA/nArVmpX6ZYU/erp5Bv6P/UfF/YnL4XjO/us0Qt4j9S50lETi3+tg0USOewDNPU0QL7YXSzR2tOarcZwujjUVwepUcKUtVd+gMF2NVP3q/4uiIJ4pEqXAMpF+Rk6dMWi7HiBq3K4JwOQXEOi1xNndr902GJFhkBPSLKs4fbIhBZw6e3TD3gkTi/Bp0+zASqoomsANwkGiYnBJxnM20Tn18AF2pIx8vvt//jKk3T5NW9fvswGcTKNJ8t9nx4+3KkNb2JciVW349F0sKdg2GMfjBGMQ/e3LzwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url="http://axyj.vipgz1.idcfengye.com/WangDamo/zfbsmUrl/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String return_url = "http://wz111.free.ngrok.cc/WangDamo/zhifb/return_url.jsp";
	public static String return_url="http://axyj.vipgz1.idcfengye.com/WangDamo/pay/zfb/zfbwebDemo/return_url.jsp";
	// 签名方式
	public static String sign_type="RSA2";
	
	// 字符编码格式
	public static String charset="utf-8";
	// 返回格式
	public static String format="json";
	// 支付宝网关
	public static String gatewayUrl="https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path="C:\\";

	/*public static String alipayClient;
	static {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	}*/

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

