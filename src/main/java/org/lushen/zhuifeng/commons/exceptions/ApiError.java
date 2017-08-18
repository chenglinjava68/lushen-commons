package org.lushen.zhuifeng.commons.exceptions;

import org.lushen.zhuifeng.commons.utils.JacksonJsonParser;


/**
 * 异常信息对象
 * 
 * @author hlm
 */
public final class ApiError {

	private int errcode;
	
	private String msg;

	public ApiError(int errcode, String msg) {
		super();
		this.errcode = errcode;
		this.msg = msg;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String toJsonString() {
		try {
			return JacksonJsonParser.build().parseAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return toJsonString();
	}

}
