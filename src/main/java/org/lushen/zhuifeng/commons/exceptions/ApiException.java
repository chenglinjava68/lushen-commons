package org.lushen.zhuifeng.commons.exceptions;

/**
 * Api调用异常
 * 
 * @author hlm
 */
@SuppressWarnings("serial")
public final class ApiException extends RuntimeException {
	
	public ApiException(String msg) {
		super(msg);
	}

}
