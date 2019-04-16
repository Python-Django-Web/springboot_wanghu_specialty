package com.cwh.springbootMybatis.weixin.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.cwh.springbootMybatis.weixin.controller.WxSettingController;
import com.cwh.springbootMybatis.weixin.entity.po.Token;

/**
 * 
 * @author wanghu
 *
 */
public class StatisticsUtil extends StringUtil {
	private static final Logger log = Logger.getLogger(StatisticsUtil.class);

	public final static String statistics_create_url = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=ACCESS_TOKEN";

	public static Map<String, String[]> statisticsUser(Map<String, Object> map) {
		Map<String, String[]> m = new HashMap<String, String[]>();
		String[] dates = new String[7];
		String[] nums = new String[7];
		boolean result = false;
		WxSettingController wc = new WxSettingController(); // 获取数据库appid等微信参数
		CommonUtil commonUtil = new CommonUtil();
		StringUtil st = new StringUtil();
		Token token = commonUtil.getToken(st.getSetting().getAppid(), st
				.getSetting().getAppsecret());
		String url = statistics_create_url.replace("ACCESS_TOKEN",
				token.getAccessToken());
		String jsonUser = JSONObject.fromObject(map).toString();
		System.out.println("获取微信用户分析数据jsonStr=" + jsonUser);
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonUser);
		Iterator it = jsonObject.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			System.out.println(key);
			String value = jsonObject.getString(key);
			System.out.println(value);
			JSONArray json = JSONArray.parseArray(value);
			if (json.size() > 0) {
				for (int i = 0; i < json.size(); i++) {
					com.alibaba.fastjson.JSONObject j = json.getJSONObject(i);
					System.out.println(j.get("ref_date"));
					System.out.println(j.get("cumulate_user"));
					String a = (String) j.get("ref_date");
					String b = String.valueOf(j.get("cumulate_user"));
					System.out.println("a=" + a);
					System.out.println(i);
					System.out.println("b=" + b);
					dates[i] = a;
					nums[i] = b;
				}
			}
		}
		m.put("dates", dates);
		m.put("nums", nums);
		return m;
	}

	public static void main(String[] args) {
		//String str="{\"TI\":[{\"value\":\"a\",\"count\":10},{\"value\":\"a2\",\"count\":15},{\"value\":\"a1\",\"count\":15}]}";
		String str = "[{\"gnormsId\":0,\"goodsCino\":\"\",\"goodsGnormsThree\":\"\",\"goodsId\":32482,\"goodsPubtime\":1545209804,\"harlan_gnorms\":{\"gnormsvalThree\":\"10kg/瓶\",\"gnormsId\":30056,\"goodsMarketprice\":66000,\"goodsPics\":[\"20181219/cb220c9b-d3d7-4e69-8600-318009711f75.png\",\"20181219/334474ba-0826-460d-9960-4b895f785443.png\",\"20181219/11abfd79-0c59-43a6-9901-5a21121f2397.png\"],\"goodsId\":32482,\"goodsMallprice\":59400,\"goodsStatusTime\":0,\"harlan_gnorms_detailst\":{\"goodsDiscountprice\":0,\"goodsPayway\":0,\"gnormsId\":30056,\"goodsBigPic\":\"\",\"goodsSamplePic\":\"\",\"goodsId\":32482,\"goodsColorReportPic\":\"\",\"goodsTestReportPic\":\"\",\"goodsTransport\":\"\",\"goodsUsedesPic\":\"\",\"goodsTestReportVideo\":\"\",\"goodsMakeStatePic\":\"\",\"goodsNumber\":0,\"goodsUvReportPic\":\"\",\"goodsIsdiscount\":0},\"gnormsNum\":0,\"goodsIshotsale\":0,\"goodsStock\":1000,\"goodsType\":0,\"gnormsvalOne\":\"500g/瓶\",\"goodsStatus\":1,\"sortId\":0,\"pubtime\":1545209896,\"goodsPicsList\":[\"http://www.chinaharlan.com/upload/20181219/cb220c9b-d3d7-4e69-8600-318009711f75.png\",\"http://www.chinaharlan.com/upload/20181219/334474ba-0826-460d-9960-4b895f785443.png\",\"http://www.chinaharlan.com/upload/20181219/11abfd79-0c59-43a6-9901-5a21121f2397.png\"],\"adminId\":8,\"harlan_gnorms_details\":{\"goodsLeadIon\":\"\",\"gnormsId\":30056,\"goodsStrength\":\"\",\"goodsMercuryIon\":\"\",\"goodsPsa\":\"18.46000\",\"goodsId\":32482,\"goodsColorlight\":\"\",\"goodsOther\":\"\",\"goodsNickelIon\":\"\",\"goodsPic\":\"http://www.chinaharlan.com/upload/20181219/70a1ef21-3d98-4d6f-b019-4c89aaa6f09a.png\",\"goodsTinIon\":\"\",\"goodsSalinity\":\"\",\"goodsTime\":7,\"goodsCobaltIon\":\"\",\"goodsConductivity\":\"\",\"goodsWater\":\"\",\"goodsPurity\":\"95%\",\"goodsExactquality\":\"206.13100\",\"goodsChlorine\":\"\",\"goodsKetone\":\"\",\"goodsAntimonyIon\":\"\",\"goodsSpec\":\"1kg\",\"goodsSolubility\":\"\",\"goodsInsoluble\":\"\",\"goodsMoleculeweight\":\"206.28100\",\"goodsCadmiumIon\":\"\",\"goodsFacade\":\"无色透明液体\",\"goodsCopperIon\":\"\",\"goodsSolid\":\"\",\"goodsZincIon\":\"\",\"goodsAppScheme\":\"\",\"goodsBismuthIon\":\"\"},\"gnormsvalTwo\":\"5kg/瓶\",\"goodsUnit\":\"kg\",\"memberId\":0,\"isFavorite\":false},\"goodsType\":0,\"goodsEnUsName\":\"Zimtaldehyd-diaethylacetal\",\"goodsCas\":\"7148-78-9\",\"goodsGnormsTwo\":\"\",\"goodsGnormsOne\":\"\",\"goodsStatus\":1,\"goodsZhCnName\":\"肉桂醛二乙缩醛\",\"gclassId\":163,\"gnormsList\":[],\"goodsName\":\"肉桂醛二乙缩醛\",\"memberId\":0}]";
		JSONArray json = JSONArray.parseArray(str);
		if(json.size()>0){
			for (int i = 0; i < json.size(); i++) {
				com.alibaba.fastjson.JSONObject j=json.getJSONObject(i);
				System.out.println(j.get("gnormsId"));
			}
		}
		/*try {
			JSONObject obj=JSONObject.fromObject(str);
			System.out.println(obj.get("goodsCas"));;
			System.out.println(obj);
			Iterator it=obj.keys();
			while (it.hasNext()) {
				String key=(String)it.next();
				System.out.println(key);
				String value=obj.getString(key);
				System.out.println(value);
				JSONArray json = JSONArray.parseArray(value);
				if(json.size()>0){
					for (int i = 0; i < json.size(); i++) {
						com.alibaba.fastjson.JSONObject j=json.getJSONObject(i);
						System.out.println(j.get("value")+"=");
					}
				}
						
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
	}
}
