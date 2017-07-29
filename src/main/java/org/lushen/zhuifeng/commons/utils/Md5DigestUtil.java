package org.lushen.zhuifeng.commons.utils;

import java.security.MessageDigest;

/**
 * MD5加密
 *
 * @author hlm
 */
public final class Md5DigestUtil {

	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * MD5加密
	 * 
	 * @param arg
	 * @return
	 */
	public static final String encode(String arg) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(arg.getBytes());
			byte[] bytes = digest.digest();
			char[] chars = new char[bytes.length * 2];
			for (int i=0, k=0 ; i < bytes.length; i++) {
				byte byte0 = bytes[i];
				chars[k++] = hexDigits[byte0 >>> 4 & 0xf];
				chars[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(chars);
		} catch (Exception e) {
			return "";
		}
	}

}
