package com.reinertisa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {
	
	@Id
	@Column(name = "role_id")
	private int roleId;
	
	private String role;
	
	public UserRole() {
		// TODO Auto-generated constructor stub
	}
	
	
	public UserRole(int roleId) {
		this.roleId = roleId;
	}


	public UserRole(String role) {

		this.role = role;
	}

	public UserRole(int roleId, String role) {
	
		this.roleId = roleId;
		this.role = role;
	}


	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", role=" + role + "]";
	}

}
