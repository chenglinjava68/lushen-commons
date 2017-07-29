package org.lushen.zhuifeng.commons.enums;


/**
 * 	HTTP状态码和状态信息枚举
 * 
 * @author hlm
 */
public enum HttpStatusEnum {
	
	SYSTEM_BUSINESS(-1, "系统繁忙"),
	
	SYSTEM_OK(200, "成功"),
	
	SYSTEM_BAD_REQUEST(400, "请求参数有误"),
	
	SYSTEM_UN_ANTHORIZED(401, "需要用户验证"),
	
	SYSTEM_FORBIDDEN(403, "拒绝执行"),
	
	SYSTEM_NOT_FOUND(404, "资源不存在"),
	
	SYSTEM_SERVER_ERROR(500, "需要用户验证");
	
	private int code;
	
	private String msg;

	private HttpStatusEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
