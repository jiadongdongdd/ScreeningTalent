package com.idsmanager.commons.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class DateUtils {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    
    public static final String DEFAULT_DATE_FORMAT_TWO = "yyyy.MM.dd";


    private DateUtils() {
    }

    public static Date now() {
        return new Date();
    }


    //Create new  SimpleDateFormat
    private static SimpleDateFormat newDateFormat(String pattern) {
        return new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
    }

    public static String toDateTime(Date date) {
        return toDateText(date, DEFAULT_DATE_TIME_FORMAT);
    }


    public static String toDateText(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        SimpleDateFormat dateFormat = newDateFormat(pattern);
        return dateFormat.format(date);
    }


    public static String toDate(Date date) {
        return toDateText(date, DEFAULT_DATE_FORMAT);
    }

    public static Date getDate(String dateText) {
        return getDate(dateText, DEFAULT_DATE_FORMAT);
    }
    
    public static Date getDateTwo(String dateText) {
        return getDate(dateText, DEFAULT_DATE_FORMAT_TWO);
    }

    public static Date getDate(String dateText, String pattern) {
        if (StringUtils.isEmpty(dateText)) {
            return null;
        }
        SimpleDateFormat dateFormat = newDateFormat(pattern);
        try {
            return dateFormat.parse(dateText);
        } catch (ParseException e) {
            throw new IllegalStateException("Parse date from [" + dateText + "," + pattern + "] failed", e);
        }
    }
    
    /**
    * 获得指定日期的年份
    * @param date
    * @return
    */
    public static int getYearByDate(Date date) {

    Calendar cal = Calendar.getInstance();

    cal.setTime(date);

    return cal.get(Calendar.YEAR);

    }
    
    /**
    * 获得指定日期的月份
    * 
    * @param date
    * @return
    */
    public static int getMonthByDate(Date date) {

    Calendar cal = Calendar.getInstance();

    cal.setTime(date);

    return cal.get(Calendar.MONTH);

    }
    
    /**
    * 获得指定日期的所在月份当前的天数
    * 
    * @param date
    * @return
    */
    public static int getDayInMonthByDate(Date date) {

    Calendar cal = Calendar.getInstance();

    cal.setTime(date);

    return cal.get(Calendar.DAY_OF_MONTH);

    }
    
    /**
    * 计算两个日期相差的月数（具体细分到天数的差别）
    * @param date1
    * @param date2
    * @return
    */
    public static int getDiffMonths(Date date1, Date date2) {

      int iMonth = 0;

      int flag = 0;

      try {

       Calendar objCalendarDate1 = Calendar.getInstance();

       objCalendarDate1.setTime(date1);

       Calendar objCalendarDate2 = Calendar.getInstance();

       objCalendarDate2.setTime(date2);

       if (objCalendarDate2.equals(objCalendarDate1))

        return 0;

       if (objCalendarDate1.after(objCalendarDate2)) {

        Calendar temp = objCalendarDate1;

        objCalendarDate1 = objCalendarDate2;

        objCalendarDate2 = temp;

       }

       if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1

         .get(Calendar.DAY_OF_MONTH))

        flag = 1;

       if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1

         .get(Calendar.YEAR))

        iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1

          .get(Calendar.YEAR))

          * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)

          - objCalendarDate1.get(Calendar.MONTH);

       else

        iMonth = objCalendarDate2.get(Calendar.MONTH)

          - objCalendarDate1.get(Calendar.MONTH) - flag;

      } catch (Exception e) {

       e.printStackTrace();

      }

      return iMonth;

    }
    
    
    /** 
    * 将Date对象转换为yyyy-MM-dd字符串 
    * 
    * @param d 
    * @return 
    */ 
    public static String dateToStr(Date d) { 
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    String dateNowStr = sdf.format(d); 
    return dateNowStr; 
    } 

    /** 
    * 将yyyy-MM-dd HH:mm:ss字符串转换为Date对象 
    * 
    * @param d 
    * @return 
    */ 
    public static Date strToDate(String dstr) { 
    Date today = null; 
    try { 
    // String str = "2012-1-13 17:26:33"; //要跟上面sdf定义的格式一样 
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    today = sdf.parse(dstr); 
    } catch (Exception e) { 
    today = new Date(); 
    } 
    return today; 
    } 
    
    /** 
    * 判断a是否大于b 
    * 
    * @param a 
    * @param b 
    * @return 
    */ 
    public static Date pkdatelt(Date a, Date b) { 
	    try { 
	    if (a.before(b)) { 
	    	return b;
	    }else{ 
	    	return a;
	    }
	    } catch (Exception e) { 
	
	    } 
	    return b; 
    } 
    
}
