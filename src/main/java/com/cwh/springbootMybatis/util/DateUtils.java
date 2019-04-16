package com.cwh.springbootMybatis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User:jaywechen
 * Date:2017/7/28
 * Time:17:55
 */
public class DateUtils {

    public static final String time_pattern_all = "yyyy-MM-dd HH:mm:ss";

    public static final String normal = "yyyy/MM/dd HH:mm:ss.SSS";

    public static final String standard_time_pattern = "yyyyMMddHHmmssSSS";

    public static String format(Date date,String pattern){
        if (date != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(date);
        }
        return null;
    }

    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }
    
	public static String strToDate(String end_time){
		StringBuffer buffer = new StringBuffer();
		buffer.append(end_time.substring(0,4))
				.append("-").append(end_time.substring(4,6))
				.append("-").append(end_time.substring(6,8))
				.append(" ").append(end_time.substring(8,10))
				.append(":").append(end_time.substring(10,12))
				.append(":").append(end_time.substring(12,14));
		return buffer.toString();
	}

    public static void main(String[] args) {
    	System.out.println(strToDate("20171130140538"));
        String a=String.valueOf(getSecondTimestamp(new Date()));
        System.out.println(a);
        System.out.println(a.length());
        for (int i=0;i<=100;i++) {
			
        	System.out.println(format(new Date(),time_pattern_all));
		}
    }
}
