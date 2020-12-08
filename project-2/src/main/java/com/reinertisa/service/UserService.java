package com.reinertisa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;


public interface UserService {

	public String signup(HttpServletRequest req);
	public String getAllUsers();
	public User getUserByUsername(String username);
	
	
	public String signin(HttpServletRequest req);
	public String signout(HttpServletRequest req);
	public String forgotPassword(HttpServletRequest req);
	
	
	public boolean createRoleTable(UserRole role);
}
