package org.lushen.zhuifeng.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间处理类
 *
 * @author hlm
 */
public final class DateUtil {

	/**
	 * 格式化输出日期
	 * 
	 * @param date
	 * @param format
	 * 
	 * @return String
	 */
	public static final String format(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	 * 格式化输出当前时间
	 * 
	 * @param format
	 * 
	 * @return String
	 */
	public static final String formatNow(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}

}
