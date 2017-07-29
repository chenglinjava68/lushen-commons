package org.lushen.zhuifeng.commons.jwt;

/**
 * 默认jwt授权认证信息bean
 * 
 * @author hlm
 */
public final class JwtDefaultAuthorizedUser extends AuthorizedUser implements JwtAuthorizedUser {
	
	private String uuid;
	
	private long timestamp;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String getUniqueID() {
		return this.getUserId();
	}

}
