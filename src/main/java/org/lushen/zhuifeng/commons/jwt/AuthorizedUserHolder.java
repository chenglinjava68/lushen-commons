package org.lushen.zhuifeng.commons.jwt;

/**
 * 线程私有 “登录用户” 信息容器
 * 
 * @author hlm
 */
public final class AuthorizedUserHolder {
	
	private static final ThreadLocal<AuthorizedUser> currentUserHolder = new ThreadLocal<AuthorizedUser>();
	
	/**
	 * 保存当前登录用户
	 */
	public static final void put(AuthorizedUser authorizedUser) {
		currentUserHolder.set(authorizedUser);
	}
	
	/**
	 * 获取当前登录用户
	 */
	public static final AuthorizedUser get() {
		return currentUserHolder.get();
	}

}
