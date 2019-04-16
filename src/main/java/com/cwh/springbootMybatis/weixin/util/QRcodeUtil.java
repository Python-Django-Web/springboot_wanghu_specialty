package com.cwh.springbootMybatis.weixin.util;

import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONObject;


/*import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cwh.springbootMybatis.weixin.entity.po.Token;

public class QRcodeUtil extends StringUtil{

	//日志
	private static Logger log = LoggerFactory.getLogger(QRcodeUtil.class);
	
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String QR_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	private static final String TICKET_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
	public final static String weixin_jssdk_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	
	public static String  createQrcode(String string) throws Exception{
		String ticket = null;
		CommonUtil commonUtil = new CommonUtil();
		StringUtil st = new StringUtil();
		Token token = commonUtil.getToken(st.getSetting().getAppid(), st.getSetting().getAppsecret());
		String date = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":\""+string+"\"}}}";
		JSONObject jsonObject  = CommonUtil.httpsRequest(QR_URL + token.getAccessToken(),"POST", date);
		
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				log.info("创建永久带参二维码成功 ticket:{}", ticket);
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("创建永久带参二维码失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return ticket;
	}
	
	/*public static String httpRequest(String data, int connectTimeoutMs,
			int readTimeoutMs, String url) throws Exception {
		BasicHttpClientConnectionManager connManager;
		connManager = new BasicHttpClientConnectionManager(RegistryBuilder
				.<ConnectionSocketFactory> create()
				.register("http",
						PlainConnectionSocketFactory.getSocketFactory())
				.register("https",
						SSLConnectionSocketFactory.getSocketFactory()).build(),
				null, null, null);
		HttpClient httpClient = HttpClientBuilder.create()
				.setConnectionManager(connManager).build();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(readTimeoutMs)
				.setConnectTimeout(connectTimeoutMs).build();
		httpPost.setConfig(requestConfig);
		StringEntity postEntity = new StringEntity(data, "UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 ");
		httpPost.setEntity(postEntity);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		return EntityUtils.toString(httpEntity, "UTF-8");
	}*/
	public static String showQrcode(String ticket) {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("ticket", Qrutils.urlEncode(ticket, "utf-8"));
		String qr = null;
		try {
			qr = Qrutils.setParmas(params, TICKET_URL, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qr;
	}
}
