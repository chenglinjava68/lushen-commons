package org.lushen.zhuifeng.commons.utils;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

/**
 * 从HttpServletRequest读取所有主体数据
 *
 * @author hlm
 */
public final class HttpRequestReader {

	/**
	 * 读取request的字符流
	 * 
	 * @param request	HTTP请求对象
	 */
	public static final String readRequestBody(HttpServletRequest request) {
		
		try(InputStream is = request.getInputStream()) {
			
			StringBuffer sb = new StringBuffer(3000);
			
			byte[] bytes = new byte[4096];
			int size = 0;
			while ((size = is.read(bytes)) > 0) {
				sb.append(new String(bytes, 0, size, "UTF-8"));
			}
			
			return sb.toString();
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		}
	}
	

	/**
	 * 读取request的字符流
	 * 
	 * @param request	HTTP请求对象
	 * @param buffer	字符串缓冲区大小
	 */
	public static final String readRequestBody(HttpServletRequest request, int buffer) {
		
		try(InputStream is = request.getInputStream()) {
			
			StringBuffer sb = new StringBuffer(buffer);
			byte[] bytes = new byte[4096];
			int size = 0;
			while ((size = is.read(bytes)) > 0) {
				sb.append(new String(bytes, 0, size, "UTF-8"));
			}
			
			return sb.toString();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
