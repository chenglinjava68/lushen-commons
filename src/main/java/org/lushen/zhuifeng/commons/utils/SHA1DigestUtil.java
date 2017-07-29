package org.lushen.zhuifeng.commons.utils;

import java.security.MessageDigest;

/**
 * SHA加密
 * 
 * @author hlm
 */
public final class SHA1DigestUtil {

	/**
	 * SHA加密
	 * 
	 * @param arg
	 * 
	 * @return String
	 */
	public static final String SHA1Encode(String arg) {
		
		try {
			
			byte[] bytes = MessageDigest.getInstance("SHA-1").digest(arg.getBytes());
			
			StringBuffer buf = new StringBuffer(bytes.length * 2);
			
			for (int i = 0; i < bytes.length; i++) {
				if (((int) bytes[i] & 0xff) < 0x10) {
					buf.append("0");
				}
				buf.append(Long.toString((int) bytes[i] & 0xff, 16));
			}
			
			return buf.toString().toLowerCase();
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		}
	}

}
