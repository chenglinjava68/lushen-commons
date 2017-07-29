package org.lushen.zhuifeng.commons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http Cookie 工具类
 *
 * @author hlm
 */
public class HttpCookieUtil {

	/**
	 * 读取名称为name的客户端cookie
	 */
	public static final String readCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			if(name.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param secondMaxAge
	 * @param path
	 */
	public static final void setCookie(HttpServletResponse response, String name, String value, int secondMaxAge, String path, boolean httpOnly) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(secondMaxAge);
		cookie.setPath(path);
		cookie.setHttpOnly(httpOnly);
		response.addCookie(cookie);
	}

	
	/**
	 * 删除cookie
	 * 
	 * @param cookie
	 */
	public static final void removeCookie(Cookie cookie) {
		cookie.setMaxAge(0);
	}

}
