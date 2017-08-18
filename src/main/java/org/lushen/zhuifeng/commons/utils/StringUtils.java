package org.lushen.zhuifeng.commons.utils;

/**
 * 字符串工具类
 * 
 * @author hlm
 */
public class StringUtils {
	
	/**
	 * 获取字符串byte[]
	 * 
	 * @param arg
	 * @param charset
	 * @return byte[]
	 * @throws Exception
	 */
	public static final byte[] getBytes(String arg, String charset) throws Exception {
		if(ObjectUtils.containsNull(arg, charset)) {
			throw new NullPointerException();
		}
		return arg.getBytes(charset);
	}

	/**
	 * 字符串首字母转小写
	 * 
	 * @param arg
	 * @return String
	 */
	public static final String toLowerCaseFirstChar(String arg) {
		if(ObjectUtils.isNull(arg)) {
			return null;
		}
		if(arg.length()==0) {
			return arg;
		}
		if(Character.isLowerCase(arg.charAt(0))) {
			return arg;
		}
		char[] chars = arg.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		return new String(chars);
	}
	
	/**
	 * 字符串首字母转大写
	 * 
	 * @param arg
	 * @return String
	 */
	public static final String toUpCaseFirstChar(String arg) {
		if(ObjectUtils.isNull(arg)) {
			return null;
		}
		if(arg.length()==0) {
			return arg;
		}
		if(Character.isUpperCase(arg.charAt(0))) {
			return arg;
		}
		char[] chars = arg.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return new String(chars);
	}
	
	/**
	 * 连接字符串
	 * 
	 * @param args
	 * @return String
	 */
	public static final String join(Object... args) {
		return org.apache.commons.lang3.StringUtils.join(args);
	}
	
	/**
	 * 判断字符串是否相同
	 * 
	 * @param arg1
	 * @param arg2
	 * @return boolean
	 */
	public static final boolean equals(String arg1, String arg2) {
		if(ObjectUtils.isNull(arg1)) {
			return false;
		}
		if(ObjectUtils.isNull(arg2)) {
			return false;
		}
		return arg1.equals(arg2);
	}

}
