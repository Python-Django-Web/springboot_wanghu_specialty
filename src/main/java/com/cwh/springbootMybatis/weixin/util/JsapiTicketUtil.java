package com.cwh.springbootMybatis.weixin.util;

import java.io.IOException;

import net.sf.json.JSONObject;

/*import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;*/

import com.cwh.springbootMybatis.weixin.entity.po.Token;


public class JsapiTicketUtil {
	
	private static final String appId = "wx44e9ad003c3f9940";
	private static final String appSecret = "4492ebf1221d8d3c4da87b1e1e5954c2";
	public final static String weixin_jssdk_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	 /**
     * 
     * 获取jsapi_ticket
     * 
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static String getJsapiTicket() throws IOException {
    	String jsapi_ticket = null;
    	
    	CommonUtil CommonUtil = new CommonUtil();
		Token token = CommonUtil.getToken(appId, appSecret);
		
        String url = weixin_jssdk_ticket_url.replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", null);
        
        if (null != jsonObject) {
        	 jsapi_ticket = jsonObject.getString("ticket");
			/*if (0 == errorCode) {
				result = true;
				log.info("修改分组名成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				log.error("修改分组名失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}*/
		}
        return jsapi_ticket;
    }
	
}
