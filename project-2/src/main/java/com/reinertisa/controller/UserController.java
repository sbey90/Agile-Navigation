package com.reinertisa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.reinertisa.model.User;

public interface UserController {

	public String signup(HttpServletRequest req);
	public User getUser(User user, HttpServletRequest req);
	public String getAllUsers();
	
	public String signin(HttpServletRequest req);
	public String signout(HttpServletRequest req);
	public String forgotPassword(HttpServletRequest req);
	
	
}
