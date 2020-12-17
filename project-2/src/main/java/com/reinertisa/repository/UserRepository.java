package com.reinertisa.repository;

import java.util.List;

import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;

public interface UserRepository {
	
	void save (User user);
	void save(UserRole role);
	List<User> findAll();
	List<User> findAllByUsername(String username);
	User findByUsername(String username);
	User findUserByUsernameAndPassword(String username, String password);
	User findByFirstNameAndLastName(String firstName, String lastName);
	User findUserByEmail(String email);
	User findUserByUserId(int userId);
	
	boolean updateUser(User user);
	boolean updateCareer(int userId, double salary, int roleId);
	
	public boolean isAvailableEmail(String email);
	public boolean isAvailableUsername(String username); 
}
