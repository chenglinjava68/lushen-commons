package org.lushen.zhuifeng.commons.exceptions;

import net.sf.json.JSONObject;


/**
 * 异常信息对象
 * 
 * @author hlm
 */
public final class ApiError {
	
	private String json;

	private int errcode;
	
	private String msg;

	public ApiError(int errcode, String msg) {
		super();
		this.errcode = errcode;
		this.msg = msg;
		
		//缓存，不再每次转换
		JSONObject object = new JSONObject();
		object.put("errcode", errcode);
		object.put("msg", msg);
		this.json = object.toString();
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
		return this.json;
	}
	
}
