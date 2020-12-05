package com.reinertisa.repository;

import java.util.List;

import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;

public interface UserRepository {
	
	void save (User user);
	void save(UserRole role);
	List<User> findAll();
	User findByName(String username);
}
