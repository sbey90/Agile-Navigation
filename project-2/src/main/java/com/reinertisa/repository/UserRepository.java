package com.reinertisa.repository;

import java.util.List;

import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;

public interface UserRepository {
	
	void save (User user);
	void save(UserRole role);
	List<User> findAll();
	User findByUsername(String username);
	User findUserByUsernameAndPassword(String username, String password);
	User findUserByEmail(String email);
	User findUserByUserId(int userId);
	
	boolean updateUser(User user);
	
	public boolean isAvailableEmail(String email);
	public boolean isAvailableUsername(String username); 
}
