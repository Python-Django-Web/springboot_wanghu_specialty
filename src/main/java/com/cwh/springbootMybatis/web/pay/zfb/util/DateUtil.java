package com.cwh.springbootMybatis.web.pay.zfb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 时间的计算
	 * @param sign_date_sql
	 * @param sign_date_now
	 * @return
	 * @throws ParseException
	 */
	public static int differentDaysByMillisecond(String sign_date_sql,
			String sign_date_now) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		java.util.Date date1 = sdf.parse(sign_date_sql);
		java.util.Date date2 = sdf.parse(sign_date_now);
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}
	
	//获取时间格式的字符串
	public static String Obtain_date(String format){
		SimpleDateFormat dft=new SimpleDateFormat(format);
		Date date=new Date();
		return dft.format(date);
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(differentDaysByMillisecond("2017-10-16",
				"2017-10-14"));
		System.out.println(Obtain_date("yyyy年MM月dd日 HH:mm:ss"));
		
	}
}
