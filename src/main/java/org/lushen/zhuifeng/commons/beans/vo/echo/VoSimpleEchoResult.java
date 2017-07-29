package org.lushen.zhuifeng.commons.beans.vo.echo;

import org.lushen.zhuifeng.commons.beans.vo.VoEchoResult;

import net.sf.json.JSONObject;

/**
 * 简单的响应对象
 * 
 * @author hlm
 */
@SuppressWarnings("serial")
public final class VoSimpleEchoResult implements VoEchoResult {

	private int errcode;
	
	private String msg;

	public VoSimpleEchoResult() {
		super();
	}

	public VoSimpleEchoResult(int errcode, String msg) {
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

	@Override
	public String toJson() {
		return JSONObject.fromObject(this).toString();
	}

	@Override
	public String toString() {
		return toJson();
	}

}