package com.cwh.springbootMybatis.util;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Date:11:37 2017/11/15
 */
public class RDateUtils {

    public final static String DATETIME_PATTERN = "yyyyMMddHHmmss";

    public final static String TIME_STAMP_PATTERN = "yyyyMMddHHmmssSSS";

    public final static String DATE_PATTERN = "yyyyMMdd";

    public final static String TIME_PATTERN = "HHmmss";

    public final static String STANDARD_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static String STANDARD_DATETIME_PATTERN_HM = "yyyy-MM-dd HH:mm";

    public final static String STANDARD_DATE_PATTERN = "yyyy-MM-dd";

    public final static String STANDARD_TIME_PATTERN = "HH:mm:ss";

    public final static String STANDARD_DATETIME_PATTERN_SOLIDUS = "yyyy/MM/dd HH:mm:ss";

    public final static String STANDARD_DATETIME_PATTERN_SOLIDUS_HM = "yyyy/MM/dd HH:mm";

    public final static String STANDARD_DATE_PATTERN_SOLIDUS = "yyyy/MM/dd";

    private RDateUtils() {
        super();
    }

    public static Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String currentDatetime() {
        return formatDate(new Date());
    }

    public static Timestamp parseDate(String dateStr, String pattern) throws ParseException {
        Date d = RDateUtils.parse(dateStr, pattern);
        return new Timestamp(d.getTime());
    }

    public static Timestamp parseDate(String dateStr) throws ParseException {
        return parseDate(dateStr, STANDARD_DATE_PATTERN);
    }

    public static java.sql.Date parseSQLDate(String dateStr, String pattern) throws ParseException {
        Date d = parse(dateStr, pattern);
        return new java.sql.Date(d.getTime());
    }

    public static java.sql.Date parseSQLDate(String dateStr) throws ParseException {
        Date d = parse(dateStr, STANDARD_DATE_PATTERN);
        return new java.sql.Date(d.getTime());
    }

    public static Timestamp getFutureTime(int month) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, month);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 显示今天时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String today() {
        return formatDateTime(new Date());
    }

    public static String formatDate(Timestamp t) {
        return formatDate(new Date(t.getTime()));
    }

    public static String formatDate(Timestamp t, String pattern) {
        return formatDate(new Date(t.getTime()), STANDARD_DATE_PATTERN);
    }

    public static String formatDateTime(Timestamp t) {
        return formatDate(new Date(t.getTime()), STANDARD_DATETIME_PATTERN);
    }

    /**
     * 格式化日期
     * @param date
     * @return yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        return formatDate(date, STANDARD_DATE_PATTERN);
    }
    public static String formatDate2(Date date) {
        return formatDate(date, STANDARD_DATETIME_PATTERN_HM);
    }
    /**
     * 格式化日期
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, STANDARD_DATETIME_PATTERN);
    }

    /**
     * 格式化日期
     * @param date 日期
     * @param pattern 格式
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null)
            return null;
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 解析日期
     * @param dateStr yyyy-MM-dd
     * @return
     */
    public static Date parse(String dateStr) {
        return parse(dateStr, STANDARD_DATE_PATTERN);
    }

    /**
     * 解析日期
     * @param dateStr yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseTime(String dateStr){
        return parse(dateStr, STANDARD_DATETIME_PATTERN);
    }

    public static Date parse(String dateStr, String pattern) {

        try {
            DateFormat format = new SimpleDateFormat(pattern);
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 当月的第一天
     *
     * @return
     */
    public static String firstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return RDateUtils.formatDate(calendar.getTime()) + " 00:00:00";
    }

    /**
     * 当月的最后一天
     *
     * @return
     */
    public static String lastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return RDateUtils.formatDate(calendar.getTime()) + " 23:59:59";
    }
    /**
     * 开始日期处理
     * @param sDate
     * @return
     */
    public static String turnStringBDate(String sDate){
    	if(null!=sDate&&!"".equals(sDate)){
    	sDate+=" 00:00:00";
    	}
    	return sDate;
    }
    /**
     * 结束日期处理
     * @param eDate
     * @return
     */
    public static String turnStringEDate(String eDate){
    	if(null!=eDate&&!"".equals(eDate)){
    	eDate+=" 23:59:59";
    	}
    	return eDate;
    }
    public static String turnStringEDate1(String eDate){
    	if(null!=eDate&&!"".equals(eDate)){
        	String[] dates=eDate.split(" ");
        	eDate=dates[0];
        	}
        	return eDate;
    }
    /**
     * findDates 
     * 返回到今天为止的30天日期
     * @return List<String>
     */
    @SuppressWarnings("static-access")
	public static List<String> findDates(){
    	 SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATE_PATTERN);
         Date today = new Date();
         String sEnd = sdf.format(today);//当前日期
         //获取三十天前日期
         Calendar theCa = Calendar.getInstance();
          theCa.setTime(today);
         theCa.add(theCa.DATE, -29);//最后一个数字29可改，30天的意思
         Date start = theCa.getTime();
         String sBegin = sdf.format(start);//三十天之前日期
         List<String>lDate = new ArrayList<String>();  
         lDate.add(sBegin);
         Calendar calBegin = Calendar.getInstance();  
         // 使用给定的 Date 设置此 Calendar 的时间  
         sdf.setLenient(false);
         Date dBegin=new Date();
         try {
      	dBegin=sdf.parse(sBegin);
      } catch (ParseException e) {
      	e.printStackTrace();
      }        
         calBegin.setTime(dBegin);  
         Date dEnd=new Date();
         try {
      	dEnd=sdf.parse(sEnd);
      } catch (ParseException e) {
      	e.printStackTrace();
      }

         Calendar calEnd = Calendar.getInstance();  
         // 使用给定的 Date 设置此 Calendar 的时间  
         calEnd.setTime(dEnd);  
         // 测试此日期是否在指定日期之后  
         while (dEnd.after(calBegin.getTime()))  
         {  
          // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
          calBegin.add(Calendar.DAY_OF_MONTH, 1);    
          lDate.add(sdf.format(calBegin.getTime()));  
         }  
         for(int i=0;i<lDate.size();i++){
         String daString=turnStringEDate(lDate.get(i));
         lDate.set(i, daString);
         }
         return lDate;  
        }
}
