package com.reinertisa.controller;

import javax.servlet.http.HttpServletRequest;


public interface UserController {

	public String signup(HttpServletRequest req);
	public String getUser(HttpServletRequest req);
	public String getAllUsers();
	
	public String updateUser(HttpServletRequest req);
	
	public String signin(HttpServletRequest req);
	public String signout(HttpServletRequest req);
	public String forgotPassword(HttpServletRequest req);
	
	
}
