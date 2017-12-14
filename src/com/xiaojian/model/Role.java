package com.xiaojian.model;

public class Role {
	private int roleId;
	private String roleName;
	private String authId;
	public int getId() {
		return roleId;
	}
	public void setId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
}
