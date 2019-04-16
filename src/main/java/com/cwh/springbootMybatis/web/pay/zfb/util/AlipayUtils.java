package com.cwh.springbootMybatis.web.pay.zfb.util;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/**
 * Created by IntelliJ IDEA
 * User:jaywechen
 * Date:2017/7/28
 * Time:15:24
 */
public class AlipayUtils {

    // get instatnce
    private static AlipayClient alipayClient = null;

    //online alipayClient
    public static AlipayClient getAlipayClientReal(String url) {
        if (alipayClient == null) {
            synchronized (AlipayUtils.class) {
                if (null == alipayClient) {
                    alipayClient = new DefaultAlipayClient(url,
                    		AlipayConfig.app_id,
                    		AlipayConfig.merchant_private_key,
                            "json", "utf-8", AlipayConfig.alipay_public_key, "RSA2");
                }
            }
        }
        return alipayClient;
    }
}
