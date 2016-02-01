package com.zlp.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	
//	private static Logger log = LoggerFactory.getLogger(TimeUtil.class);
	
	public static Date parseYmd(String arg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(arg);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date parseYmdhm(String arg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sdf.parse(arg);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date parse(String arg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(arg);
		} catch (ParseException e) {
			e.printStackTrace();
//			log.severe(e.toString());
			return null;
		}
	}
	
	public static String formatYmdhms(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static long genIdByTime() {
		return Calendar.getInstance().getTimeInMillis();
	}
	
	public static String iosDateToString(Object time) {
		if(time == null) return "";
//		String timeStr = time.toString().replace("Z\"}", "-0700");
//		String timeStr = time.toString().replace("Z\"}", "-0000");
		String timeStr = time.toString().replace("{\"$date\":\"", "");
		timeStr = timeStr.substring(0, timeStr.lastIndexOf("."));
		timeStr += "-0000";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		Date date = null;
		try {
			date = df.parse(timeStr);
			return new Timestamp(date.getTime()).toString().replace(".0", "");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
