package org.lushen.zhuifeng.commons.beans.vo.echo;

import org.lushen.zhuifeng.commons.beans.vo.VoEchoResult;

import net.sf.json.JSONObject;

/**
 * 携带信息的响应对象
 * 
 * @author hlm
 * @param <T>
 */
@SuppressWarnings("serial")
public final class VoSingleEchoResult<T> implements VoEchoResult {

	private int errcode;
	
	private String msg;
	
	private T entity;

	public VoSingleEchoResult() {
		super();
	}
	
	public VoSingleEchoResult(int errcode, String msg, T entity) {
		super();
		this.errcode = errcode;
		this.msg = msg;
		this.entity = entity;
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

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
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
