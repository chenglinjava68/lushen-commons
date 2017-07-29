package org.lushen.zhuifeng.commons.exceptions;

/**
 * 异常工具类
 * 
 * @author hlm
 */
public final class Apis {
	
	/**
	 * 抛出一个HTTP API异常
	 */
	public static final void throwApiException(ApiError apiError) {
		throw new ApiException(apiError.toJsonString());
	}

}
