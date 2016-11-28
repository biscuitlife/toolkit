package com.derekchou.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.derekchou.string.RegexUtil;

public class DateUtil {
	public static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	public static SimpleDateFormat sdf_day = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdf_day2 = new SimpleDateFormat("MM/dd/yyyy");
	public static SimpleDateFormat sdf_ymd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf_ymd00 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	public static SimpleDateFormat sdf_ymd24 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
	public static SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat sdf_mintes = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.ENGLISH);

	public static void main(String[] args) {
		System.out.println(buildIntervalStart("09/01/2016 12:12:12"));
	}
	
	public static String todayStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(calendar.YEAR) + "-" + complate(calendar.get(calendar.MONTH) + 1) + "-" + complate(calendar.get(calendar.DAY_OF_MONTH)) + " 00:00:00";
	}
	
	public static String todayEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(calendar.YEAR) + "-" + complate(calendar.get(calendar.MONTH) + 1) + "-" + complate(calendar.get(calendar.DAY_OF_MONTH)) + " 23:59:59";
	}

	public static String yestodayStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(calendar.DAY_OF_MONTH, -1);
		return calendar.get(calendar.YEAR) + "-" + complate(calendar.get(calendar.MONTH) + 1) + "-" + complate(calendar.get(calendar.DAY_OF_MONTH)) + " 00:00:00";
	}

	public static String yestodayEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(calendar.DAY_OF_MONTH, -1);
		return calendar.get(calendar.YEAR) + "-" + complate(calendar.get(calendar.MONTH) + 1) + "-" + complate(calendar.get(calendar.DAY_OF_MONTH)) + " 23:59:59";
	}
	
	public static String monthStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int day = calendar.get(Calendar.DATE);
		calendar.add(calendar.DAY_OF_MONTH, 1 - day);
		return calendar.get(calendar.YEAR) + "-" + complate(calendar.get(calendar.MONTH) + 1) + "-" + complate(calendar.get(calendar.DAY_OF_MONTH)) + " 00:00:00";
	}
	
	public static String halfMonthStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		//
		calendar.add(calendar.MONTH, -6);
		return calendar.get(calendar.YEAR) + "-" + complate(calendar.get(calendar.MONTH) + 1) + "-" + complate(calendar.get(calendar.DAY_OF_MONTH)) + " 00:00:00";
	}

	private static String complate(int dayOrMonth) {
		return dayOrMonth < 10 ? "0" + dayOrMonth : "" + dayOrMonth;
	}
	
	/**
	 * 给jfreeChat提供一个月的日期数组串
	 * @return
	 */
	public static String daysOfMonth(){
		String result = "";
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 30; i++) {
			calendar.setTime(new Date());
			calendar.add(calendar.DAY_OF_MONTH, ~i);
			if(result.length() > 0){
				result += ",";
			}
			result += "'"+complate(calendar.get(calendar.MONTH) + 1) + "-" + complate(calendar.get(calendar.DAY_OF_MONTH))+"'";
		}
		return result;
	}
	
	public static Date buildIntervalStart(String mag_interval) {
		if (mag_interval == null || mag_interval == "") {
			return null;
		}
		String pattern = RegexUtil.getValueByPattern(mag_interval, "(\\d{2}/\\d{2}/\\d{4}) - ");
		if (pattern != null && pattern != "") {
			try {
				Date date = sdf.parse(pattern+" 00:00:00");
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Date buildIntervalEnd(String mag_interval) {
		if (mag_interval == null || mag_interval == "") {
			return null;
		}
		String pattern = RegexUtil.getValueByPattern(mag_interval, "- (\\d{2}/\\d{2}/\\d{4})");
		if (pattern != null && pattern != "") {
			try {
				Date date = sdf.parse(pattern+" 23:59:59");
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * e.g.:
	 * 22 October 2014    
	 * 1:00:00 AM/1:00:00 PM
	 * @param day
	 * @param time
	 * @return
	 */
	public static Date buildTime(String day, String time) {
		Calendar calendar = Calendar.getInstance();
		try {
			String[] timeInfo = time.split(" ");
			if(timeInfo.length == 2){
				Date parseDate = sdf_mintes.parse(day + " " + timeInfo[0]);				
				calendar.setTime(parseDate);
				if("PM".equals(timeInfo)){
					calendar.add(Calendar.HOUR, 12);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar.getTime(); 
	}
	

	public static String whatDay(Date createTime) {
		Date todayDate = Calendar.getInstance().getTime();
		todayDate = new Date(todayDate.getYear(), todayDate.getMonth(), todayDate.getDate());
		Date yestoday = new Date(todayDate.getYear(), todayDate.getMonth(), todayDate.getDate() - 1);
		Calendar calendar = Calendar.getInstance();

		if (createTime.compareTo(todayDate) >= 0) {
			return "今天";
		} else if (0 >= createTime.compareTo(todayDate) && createTime.compareTo(yestoday) >= 0) {
			return "昨天";
		} else {
			String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
			Calendar cal = Calendar.getInstance();
			cal.setTime(createTime);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
				w = 0;
			return weekDays[w];
		}
	}
	
	public static String whatTime(Date createTime) {
		Date todayDate = Calendar.getInstance().getTime();
		todayDate = new Date(todayDate.getYear(), todayDate.getMonth(), todayDate.getDate());
		Date yestoday = new Date(todayDate.getYear(), todayDate.getMonth(), todayDate.getDate() - 1);
		Calendar calendar = Calendar.getInstance();
		
		if (createTime.compareTo(todayDate) >= 0) {
			return sdf_time.format(createTime);
		} else if (0 >= createTime.compareTo(todayDate) && createTime.compareTo(yestoday) >= 0) {
			return "昨天";
		} else {
			String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
			Calendar cal = Calendar.getInstance();
			cal.setTime(createTime);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
				w = 0;
			return weekDays[w];
		}
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate){    
    	int result = 0;
    	try {
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
    		smdate=sdf.parse(sdf.format(smdate));  
    		bdate=sdf.parse(sdf.format(bdate));  
    		Calendar cal = Calendar.getInstance();    
    		cal.setTime(smdate);    
    		long time1 = cal.getTimeInMillis();                 
    		cal.setTime(bdate);    
    		long time2 = cal.getTimeInMillis();         
    		long between_days=(time2-time1)/(1000*3600*24);  
    		result = Integer.parseInt(String.valueOf(between_days));           
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return Math.abs(result);
    }  
	
    /**
     * 
     * @param weekNoOfnow 较当前第几周，比如-1，3。。。
     * @return
     */
    public static Date getStartOfweek(int weekNoOfnow) {
    	Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		int nowweek = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.set(Calendar.WEEK_OF_YEAR, nowweek + weekNoOfnow-1);
		int initDay = calendar.getFirstDayOfWeek()+1;
		calendar.set(Calendar.DAY_OF_WEEK, initDay);
		calendar.set(Calendar.HOUR_OF_DAY, 0); 
		calendar.set(Calendar.SECOND, 0); 
		calendar.set(Calendar.MINUTE, 0); 
		calendar.set(Calendar.MILLISECOND, 0); 
//		System.out.println(DateUtil.sdf_ymd.format(calendar.getTime()));
		return calendar.getTime();
//    	return new Date();
	}
    /**
     * 
     * @param weekNoOfnow 较当前第几周，比如-1，3。。。
     * @return
     */
    public static Date getEndOfweek(int weekNoOfnow) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    	int nowweek = calendar.get(Calendar.WEEK_OF_YEAR);
    	calendar.set(Calendar.WEEK_OF_YEAR, nowweek + weekNoOfnow);
    	int initDay = calendar.getFirstDayOfWeek();
    	calendar.set(Calendar.DAY_OF_WEEK, initDay);
		calendar.set(Calendar.HOUR_OF_DAY, 23); 
		calendar.set(Calendar.SECOND, 59); 
		calendar.set(Calendar.MINUTE, 59); 
		calendar.set(Calendar.MILLISECOND, 0); 
//		System.out.println(DateUtil.sdf_ymd.format(calendar.getTime()));
    	return calendar.getTime();
//    	return new Date();
    }

//	public static Date dayStart(Date dayTime) {
//		if(dayTime == null){
//			return null;
//		}
//		Calendar calendar = Calendar.getInstance();
//    	calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//    	calendar.setTime(dayTime);
//		calendar.set(Calendar.HOUR_OF_DAY, 0); 
//		calendar.set(Calendar.SECOND, 0); 
//		calendar.set(Calendar.MINUTE, 0); 
//		calendar.set(Calendar.MILLISECOND, 0); 
////		System.out.println(DateUtil.sdf_ymd.format(calendar.getTime()));
//    	return calendar.getTime();
//	}
//
//	public static Date dayEnd(Date dayTime) {
//		if(dayTime == null){
//			return null;
//		}
//		Calendar calendar = Calendar.getInstance();
//    	calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//    	calendar.setTime(dayTime);
//    	calendar.set(Calendar.HOUR_OF_DAY, 23); 
//		calendar.set(Calendar.SECOND, 59); 
//		calendar.set(Calendar.MINUTE, 59); 
//		calendar.set(Calendar.MILLISECOND, 0); 
////		System.out.println(DateUtil.sdf_ymd.format(calendar.getTime()));
//    	return calendar.getTime();
//	}
    
    
}
