package com.derekchou.date;

import java.util.Date;

public class TimeUtil {

	public static String showBeforeTime(long time){
		String showtime = "刚刚";
		long now = new Date().getTime();
		if(now - time < 0){
			
		}else if(now - time < 1000 * 60){
			showtime = "一分钟内";
		}else if(now - time < 1000 * 60 * 60){
			showtime = "一小时内";
		}else if(now - time < 1000 * 60 * 60 * 24){
			showtime = "24小时内";			
		}else if(now - time < 1000 * 60 * 60 * 24 * 7){
			showtime = "一周内";			
		}else{
			showtime = "很久以前";	
		}
		
		return showtime;
	}
}
