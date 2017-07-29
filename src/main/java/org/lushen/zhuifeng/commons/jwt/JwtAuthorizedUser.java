package org.lushen.zhuifeng.commons.jwt;

/**
 * jwt授权认证信息
 * 
 * @author hlm
 */
public interface JwtAuthorizedUser {

	/**
	 * 返回唯一标识用户的ID
	 */
	String getUniqueID();
	
}
