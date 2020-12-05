package com.reinertisa.service;

import java.util.List;

import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;


public interface UserService {

	public boolean registerUser(User user);
	public List<User> getAllUsers();
	public User getUser(String name);
	
	public boolean createRoleTable(UserRole role);
}
