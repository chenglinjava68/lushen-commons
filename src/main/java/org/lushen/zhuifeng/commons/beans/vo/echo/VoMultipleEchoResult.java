package org.lushen.zhuifeng.commons.beans.vo.echo;

import java.util.List;

import org.lushen.zhuifeng.commons.beans.vo.VoEchoResult;
import org.lushen.zhuifeng.commons.utils.JacksonJsonParser;

/**
 * 携带信息集合的响应对象
 * 
 * @author hlm
 * @param <T>
 */
@SuppressWarnings("serial")
public final class VoMultipleEchoResult<T> implements VoEchoResult {

	private int errcode;
	
	private String msg;
	
	private List<T> entityList;

	public VoMultipleEchoResult() {
		super();
	}
	
	public VoMultipleEchoResult(int errcode, String msg, List<T> entityList) {
		super();
		this.errcode = errcode;
		this.msg = msg;
		this.entityList = entityList;
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

	public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

	@Override
	public String toJson() {
		try {
			return JacksonJsonParser.build().parseAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return toJson();
	}

}
