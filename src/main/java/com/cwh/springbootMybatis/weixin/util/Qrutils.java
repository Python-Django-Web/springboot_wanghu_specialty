package com.cwh.springbootMybatis.weixin.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Date;
import java.util.Set;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public class Qrutils {

	public static final String DEFAULT_CHARSET = "utf-8";

	private static String token_path = "https://api.weixin.qq.com/cgi-bin/token";
	public static String toRandom() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmsss");
		int i = (int) ((Math.random() * 9000) + 1000);
		StringBuffer sb = new StringBuffer();
		sb.append(fmt.format(new Date()));
		sb.append(i);
		return sb.toString();
	}

	public static String inputToStr(InputStream input) {
		String result = "";
		if (input != null) {
			byte[] array = new byte[1024];
			StringBuffer buffer = new StringBuffer();
			try {
				for (int index; (index = (input.read(array))) > -1;) {
					buffer.append(new String(array, 0, index, DEFAULT_CHARSET));
				}
				result = buffer.toString();
			} catch (IOException e) {
				e.printStackTrace();
				result = "";
			}
		}
		return result;
	}

	public static String setParmas(Map<String, String> map, String path,
			String charset) throws Exception {
		String result = "";
		boolean hasParams = false;
		if (path != null && !"".equals(path)) {
			if (map != null && map.size() > 0) {
				StringBuilder builder = new StringBuilder();
				Set<Entry<String, String>> params = map.entrySet();
				for (Entry<String, String> entry : params) {
					String key = entry.getKey().trim();
					String value = entry.getValue().trim();
					if (hasParams) {
						builder.append("&");
					} else {
						hasParams = true;
					}
					if (charset != null && !"".equals(charset)) {
						// builder.append(key).append("=").append(URLDecoder.(value,
						// charset));
						builder.append(key).append("=")
								.append(urlEncode(value, charset));
					} else {
						builder.append(key).append("=").append(value);
					}
				}
				result = builder.toString();
			}
		}
		return doUrlPath(path, result).toString();
	}

	private static URL doUrlPath(String path, String query) throws Exception {
		URL url = new URL(path);
		if (org.apache.commons.lang.StringUtils.isEmpty(path)) {
			return url;
		}
		if (org.apache.commons.lang.StringUtils.isEmpty(url.getQuery())) {
			if (path.endsWith("?")) {
				path += query;
			} else {
				path = path + "?" + query;
			}
		} else {
			if (path.endsWith("&")) {
				path += query;
			} else {
				path = path + "&" + query;
			}
		}
		return new URL(path);
	}

	public static String urlEncode(String source, String encode) {
		String result = source;
		try {
			result = URLEncoder.encode(source, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
