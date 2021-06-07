package com.zj.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author zhoujian
 * @corporation luke
 * @finished true
 * @finishTime 2019年12月27日 16:03:26
 * @version 1.0
 */
public abstract class DateUtilities {

	public static final String PATTERN_DATE = "yyyy-MM-dd";

	public static final String PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";

	private DateUtilities() {
	}

	/**
	 * 按照 2019-12-27 15:13:07 的格式化控制符来返回时间字符串
	 * 
	 */
	public static String formatDate(Date date) {
		return formatDate(date, PATTERN_TIME);
	}

	/**
	 * 按照给定的的格式化控制符来返回时间字符串
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static Date parseString(String dateStr) {
		return parseString(dateStr, PATTERN_TIME);
	}

	/**
	 * 按照字符串解析返回一个日期对象
	 * 
	 * @param pattern
	 *            当中的符号需要和String对象中一致， String并且需要在day和hour之间留有空格否则抛出异常
	 */
	public static Date parseString(String dateStr, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			if (!StringUtils.isEmpty(dateStr)) {
				return dateFormat.parse(dateStr);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取date为星期几
	 */
	public static String getWeekStr(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(7);
		--week;
		String weekStr = "";
		switch (week) {
		case 0:
			weekStr = "星期日";
			break;
		case 1:
			weekStr = "星期一";
			break;
		case 2:
			weekStr = "星期二";
			break;
		case 3:
			weekStr = "星期三";
			break;
		case 4:
			weekStr = "星期四";
			break;
		case 5:
			weekStr = "星期五";
			break;
		case 6:
			weekStr = "星期六";
		}
		return weekStr;
	}

	/**
	 * 返回两个日期对象之间的微秒时间间隔
	 * 
	 * @return date1-date2>0，则表明前者是在将来的时间内到达
	 */
	public static long getMillionSecondsDiff(Date date1, Date date2) {
		if ((date1 == null) || (date2 == null)) {
			return 0L;
		}

		long time1 = date1.getTime();
		long time2 = date2.getTime();

		return time1 - time2;
	}

	/**
	 * 获取两个日期对象代表的日期所差的天数 返回的结果是保留正数部分，小数部分截去 date1 - date2
	 * 
	 * @return 返回天数
	 */
	public static int getDateDiff(Date date1, Date date2) {
		if ((date1 == null) || (date2 == null)) {
			return 0;
		}
		long time1 = date1.getTime();
		long time2 = date2.getTime();

		long diffMillions = time1 - time2;

		Long longValue = new Long(diffMillions / 86400000L);
		return longValue.intValue();
	}

	/**
	 * 返回给定date一定天数前的date对象
	 * 
	 * @return 返回代表几天前的日期对象
	 */
	public static Date getDataBefore(Date date, int day) {
		if (date == null) {
			return null;
		}
		long time = date.getTime();
		time -= 86400000L * day;
		return new Date(time);
	}

	/**
	 * @returne 返回中国人口中星期几数值，从1~7分别代表周1~周日 但是在Calendar内部，1为SUNDAY
	 */
	public static int getCurrentWeekDayInt() {
		Calendar calendar = Calendar.getInstance();
		// 返回1为周日，周日为一周的开始
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		--weekDay;
		if (weekDay == 0) {
			weekDay = 7;
		}
		return weekDay;
	}

	/**
	 * @return 返回代表今天星期几的字符串
	 */
	public static String getCurrentWeekDayStr() {
		return getWeekStr(new Date());
	}

	/**
	 * @return 返回yyyy
	 */
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * @return 返回MM
	 */
	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		// 返回的月份从０ 开始的 所以＋１，星期从１开始，但是１为周日
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * @return 返回dd
	 */
	public static int getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 单独返回年份日期部分，格式为yyyymmdd
	 * 
	 * @param dateTime
	 *            必须严格匹配"yyyy*MM*dd",推荐使用{@code DateUtilities.formatDate(Date)}
	 * @return yyyymmdd
	 */
	public static String formatYearDate(String dateTime) {
		if ((dateTime != null) && (dateTime.length() >= 8)) {
			String formatDateTime = dateTime.replaceAll("-", "");
			formatDateTime = formatDateTime.replaceAll(":", "");
			String date = formatDateTime.substring(0, 8);
			return date;
		}

		return "";
	}

	/**
	 * 单独返回年份日期部分，格式为yyyymmdd
	 */
	public static String formatYearDate(Date date) {
		return formatYearDate(formatDate(date));
	}

	/**
	 * @return 返回时间yyyymmddhhmmss
	 */
	public static String formatDateNoSeparator(String dateTime) {
		if ((dateTime != null) && (dateTime.length() >= 8)) {
			String formatDateTime = dateTime.replaceAll("-", "").replace(" ", "").replaceAll(":", "");
			return formatDateTime.trim();
		}

		return "";
	}

	/**
	 * 使用默认pattern格式化Date 并返回无分隔符的字符串：2019-12-27 15:16:56 -> 20191227151656
	 * 
	 * @param date
	 *            different from formatDateTime
	 * @return
	 */
	public static String formatDateNoSeparator(Date date) {
		String dateTime = formatDate(date);
		return formatDateNoSeparator(dateTime);
	}
}