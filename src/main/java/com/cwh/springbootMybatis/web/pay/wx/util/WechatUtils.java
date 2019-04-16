package com.cwh.springbootMybatis.web.pay.wx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.cwh.springbootMybatis.util.DateUtils;
import com.cwh.springbootMybatis.web.pay.wx.bean.WeChatPayRequestBean;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA
 * User:jaywechen
 * Date:2017/8/12
 * Time:15:33
 */
public class WechatUtils {

    /**
     * function1:商户订单号
     */
    public static String getOut_trade_no() {
        SimpleDateFormat fmt = new SimpleDateFormat("MMddHHmmss");
        String format = fmt.format(new Date());
        return format;
    }

    /**
     * function2:生成随机数（for sign）
     */
    public static String getNonce_str() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmsss");

        int i = (int) ((Math.random() * 9000) + 1000);
        StringBuffer sb = new StringBuffer();
        sb.append(fmt.format(new Date()));
        sb.append(i);
        return sb.toString();
    }

    /**
     * preparations1：生成订单第一步-获取xml(微信服务器需要的xml格式)
     */
    public static String getXml(WeChatPayRequestBean bean) throws Exception {
        SortedMap<String, String> map = new TreeMap<String, String>();
        map.put("appid", Constants.appid);
        map.put("body", Constants.body);
        map.put("mch_id", Constants.mch_id);
        map.put("nonce_str", getNonce_str());
        map.put("notify_url", Constants.notify_url);
        map.put("out_trade_no", bean.getOut_trade_no());
        map.put("spbill_create_ip", bean.getSpbill_create_ip());
       // map.put("total_fee", String.valueOf(bean.getTotal_fee()));
        map.put("total_fee", "1");
        map.put("trade_type", Constants.trade_type);
        /**
         * desc微信支付签名算法sign
         */
        String sign = createSign("utf-8", map);
        map.put("sign", sign);
        /**
         * desc将Map转换为XML格式的字符串
         */
        String mapToXml = mapToXml(map);
        return mapToXml;

    }

    /**
     * preparations2：生成订单第一步-获取xml(微信服务器需要的xml格式)
     */
    public static String getJson(Map<String, String> readStringXmlOut)
            throws Exception {
        String appid = Constants.appid;
        String partnerid = Constants.mch_id;
        String prepayid = readStringXmlOut.get("prepay_id");
        String noncestr = readStringXmlOut.get("nonce_str");


        SortedMap<String, String> map = new TreeMap<String, String>();
        map.put("appid", appid);
        map.put("partnerid", partnerid);
        map.put("prepayid", prepayid);
        map.put("noncestr", noncestr);
        map.put("timestamp", String.valueOf(DateUtils.getSecondTimestamp(new Date())));
        map.put("package", "Sign=WXPay");
        String sign = createSign("utf-8", map);
        map.put("sign", sign);
        String json = toJson(map);
        System.out.println(json + "返回客户端的值");
        return json;
    }

    public static String toJson(Object object) {
        String result = JSON.toJSONString(object, config, features);
        return result;
    }

    private static final SerializeConfig config = new SerializeConfig();
    static {
        config.put(java.sql.Date.class, new SimpleDateFormatSerializer(
                "yyyy-MM-dd"));
    }

    private static final SerializerFeature[] features = {

            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.DisableCircularReferenceDetect };

    /**
     * 微信支付签名算法sign
     * @param characterEncoding
     * @param parameters
     * @return
     * @throws Exception
     */
    public static String createSign(String characterEncoding,
                                    SortedMap<String, String> parameters) throws Exception {

        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + Constants.private_key); // KEY是商户秘钥
        String sign = MD5(sb.toString()).toUpperCase();
        return sign;
    }

    // md5
    public static String MD5(String stringSignTemp) throws Exception {
        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(stringSignTemp.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100)
                    .substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 将Map转换为XML格式的字符串
     * @param data
     *            Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory
                .newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key : data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); // .replaceAll("\n|\r",
        // "");
        try {
            writer.close();
        } catch (Exception ex) {
        }
        return output;
    }


    /**
     * @description 将xml字符串转换成mapz
     * @param xml
     * @return Map
     */
    public static Map<String, String> readStringXmlOut(String xml) {
        Map<String, String> map = new HashMap<String, String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            List<Element> list = rootElt.elements();// 获取根节点下所有节点
            for (Element element : list) { // 遍历节点
                map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
            }
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
        return map;
    }


}
