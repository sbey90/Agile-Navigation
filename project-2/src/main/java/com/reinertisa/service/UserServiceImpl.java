package com.reinertisa.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;
import com.reinertisa.repository.UserRepository;



@Service("userService")
public class UserServiceImpl implements UserService{

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public UserServiceImpl() {
		logger.trace("Injection using Autowired Hero Repository in UserServiceImpl");
	}
	
	@Override
	public boolean registerUser(User user) {
		userRepository.save(user);
		return user.getUserId() != 0;
		
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public boolean createRoleTable(UserRole role) {
		userRepository.save(role);
		return false;
	}
}