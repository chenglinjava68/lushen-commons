package org.lushen.zhuifeng.commons.utils;

import java.util.UUID;

/**
 * UUID工具类
 * 
 * @author hlm
 */
public final class UuidUtils {

	/**
	 * 获取去掉'-'分隔符的32位UUID
	 * 
	 * @return String
	 */
	public static final String get32UUID() {
		char[] chars = new char[32];
		int off = 0;
		for(char ch : UUID.randomUUID().toString().toCharArray()) {
			if(ch!='-') {
				chars[off++] = ch;
			}
		}
		return new String(chars);
	}

	/**
	 * 获取36位的UUID
	 * 
	 * @return String
	 */
	public static final String get36UUID() {
		return UUID.randomUUID().toString();
	}

}
