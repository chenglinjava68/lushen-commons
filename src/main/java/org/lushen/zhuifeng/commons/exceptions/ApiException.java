package org.lushen.zhuifeng.commons.exceptions;

/**
 * Api调用异常
 * 
 * @author hlm
 */
@SuppressWarnings("serial")
public class ApiException extends RuntimeException {
	
	private ApiError apiError;
	
	public ApiException(ApiError apiError) {
		super();
		this.apiError = apiError;
	}

	public ApiError getApiError() {
		return apiError;
	}

}
