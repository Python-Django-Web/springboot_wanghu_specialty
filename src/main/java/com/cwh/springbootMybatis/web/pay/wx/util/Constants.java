package com.cwh.springbootMybatis.web.pay.wx.util;

/**
 * Created by IntelliJ IDEA
 * User:jaywechen
 * Date:2017/8/12
 * Time:15:28
 */
public interface Constants {

    String appid = "wxa4f049e539249a4b";
    String mch_id = "1487720952";
    String body = "行深金鹿";
    String notify_url = "http://61.181.89.138:33902/lava/wechat/callback.action";
   // String notify_url = "http://www.zhhshb.cn/wechat/callback.action";
    String trade_type = "APP";//MWEB
    //String trade_type = "MWEB";
    String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    String private_key = "844fd80f20ea5b97235c7982d0ff507d";
}
