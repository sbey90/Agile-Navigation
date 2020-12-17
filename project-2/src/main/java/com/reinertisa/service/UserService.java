package com.reinertisa.service;

import javax.servlet.http.HttpServletRequest;

import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;


public interface UserService {

	public String signup(HttpServletRequest req);
	public String getAllUsers();
	public String getAllUsers(HttpServletRequest req);
	
	public User getUserByUsername(String username);
	public User getUser(String firstName, String lastName);
	public User getUser(int userId);
	
	public String getUser(HttpServletRequest req);
	public String updateUser(HttpServletRequest req);
	public String updateCareer(HttpServletRequest req);
	
	
	public String signin(HttpServletRequest req);
	public String signout(HttpServletRequest req);	
	public String forgotPass(HttpServletRequest req);
	
	
	public boolean createRoleTable(UserRole role);
}
