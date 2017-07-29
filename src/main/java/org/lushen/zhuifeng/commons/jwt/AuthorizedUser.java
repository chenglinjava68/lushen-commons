package org.lushen.zhuifeng.commons.jwt;

import java.util.List;

/**
 * 当前用户信息
 * 
 * @author hlm
 */
public class AuthorizedUser {

	private String userId;
	
	private List<String> roleIdList;

	public AuthorizedUser() {
		super();
	}

	public AuthorizedUser(String userId, List<String> roleIdList) {
		super();
		this.userId = userId;
		this.roleIdList = roleIdList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

}
