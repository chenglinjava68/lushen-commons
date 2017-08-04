package org.lushen.zhuifeng.commons.utils.support;


/**
 * JSON解析异常
 * 
 * @author hlm
 */
@SuppressWarnings("serial")
public class JsonException extends Exception {

	public JsonException() {
		super();
	}

	public JsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JsonException(String message, Throwable cause) {
		super(message, cause);
	}

	public JsonException(String message) {
		super(message);
	}

	public JsonException(Throwable cause) {
		super(cause);
	}

}
