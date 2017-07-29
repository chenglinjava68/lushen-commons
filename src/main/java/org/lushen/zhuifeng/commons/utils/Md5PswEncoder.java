package org.lushen.zhuifeng.commons.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 密码加密
 * 
 * @author hlm
 */
public final class Md5PswEncoder {
	
	/**
	 * 加密
	 * 
	 * @param password	密码
	 * @param salt		密码盐
	 * 
	 * @return	加密后的密串
	 */
	public static final String encode(String password, String salt) {
		return Md5DigestUtil.encode(StringUtils.join(new String[]{password, salt, password}, "-"));
	}

	/**
	 * 验证密串
	 * 
	 * @param password	密码
	 * @param salt		密码盐
	 * @param md5Psw	密串
	 * @return
	 */
	public static final boolean validate(String password, String salt, String md5Psw) {
		return encode(password, salt).equals(md5Psw);
	}
	
}
