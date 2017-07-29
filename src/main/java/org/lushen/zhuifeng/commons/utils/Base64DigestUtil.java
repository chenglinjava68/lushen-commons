package org.lushen.zhuifeng.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Base64编码工具类
 * 
 */
public final class Base64DigestUtil {

	private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

	/**
	 * 编码
	 * 
	 * @param bytes
	 * 
	 * @return String
	 */
	public static final String encode(byte[] bytes) {
		
		int start = 0;
		int len = bytes.length;
		StringBuffer buf = new StringBuffer(bytes.length * 3 / 2);

		int end = len - 3;
		int i = start;
		int n = 0;

		while (i <= end) {
			int d = ((((int) bytes[i]) & 0x0ff) << 16) | ((((int) bytes[i + 1]) & 0x0ff) << 8) | (((int) bytes[i + 2]) & 0x0ff);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append(legalChars[d & 63]);

			i += 3;

			if (n++ >= 14) {
				n = 0;
				buf.append(" ");
			}
		}

		if (i == start + len - 2) {
			int d = ((((int) bytes[i]) & 0x0ff) << 16) | ((((int) bytes[i + 1]) & 255) << 8);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append("=");
		} else if (i == start + len - 1) {
			int d = (((int) bytes[i]) & 0x0ff) << 16;

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append("==");
		}

		return buf.toString();
	}

	/**
	 * 解码
	 * 
	 * @param arg
	 * 
	 * @return byte[]
	 */
	public static final byte[] decode(String arg) {
		
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			int i = 0;
			int len = arg.length();
			
			while (true) {
				
				while (i < len && arg.charAt(i) <= ' ') {
					i++;
				}

				if (i == len) {
					break;
				}

				int tri = (decode(arg.charAt(i)) << 18) + (decode(arg.charAt(i + 1)) << 12) + (decode(arg.charAt(i + 2)) << 6) + (decode(arg.charAt(i + 3)));
				
				bos.write((tri >> 16) & 255);
				
				if (arg.charAt(i + 2) == '=') {
					break;
				}
				
				bos.write((tri >> 8) & 255);
				
				if (arg.charAt(i + 3) == '=') {
					break;
				}
				
				bos.write(tri & 255);

				i += 4;
			}
			
			return bos.toByteArray();
			
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static int decode(char c) {
		if (c >= 'A' && c <= 'Z') {
			return ((int) c) - 65;
		} else if (c >= 'a' && c <= 'z') {
			return ((int) c) - 97 + 26;
		} else if (c >= '0' && c <= '9') {
			return ((int) c) - 48 + 26 + 26;
		} else {
			switch (c) {
				case '+':	return 62;
				case '/':	return 63;
				case '=':	return 0;
				default:	throw new RuntimeException("unexpected code: " + c);
			}
		}
	}

}

