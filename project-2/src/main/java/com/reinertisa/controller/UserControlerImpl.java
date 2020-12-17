package com.reinertisa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reinertisa.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller("userController")
public class UserControlerImpl implements UserController {

	@Autowired
	private UserService userService;

	@PostMapping("api/signup")
	public @ResponseBody String signup(HttpServletRequest req) {

		return userService.signup(req);
	}

	@PostMapping("api/signin")
	public @ResponseBody String signin(HttpServletRequest req) {
		
		return userService.signin(req);
	}


	@PostMapping("api/forgotPass")
	public @ResponseBody String forgotPassword(HttpServletRequest req) {
		return userService.forgotPass(req);
	}

	@PostMapping("api/getUser")
	public @ResponseBody String getUser(HttpServletRequest req) {
		
		return userService.getUser(req);		
	}
	
	@PutMapping("api/updateUser")
	public @ResponseBody String updateUser(HttpServletRequest req) {
		
		return userService.updateUser(req);		
	}

	@GetMapping("api/getAllUsers")
	public @ResponseBody String getAllUsers() {

		return userService.getAllUsers();
	}

	@PostMapping("api/searchUser")
	public @ResponseBody String getAllUsers(HttpServletRequest req) {
		return userService.getAllUsers(req);
	}
	
	@PutMapping("api/updateCareer")
	public @ResponseBody String updateCareer(HttpServletRequest req) {
		
		return userService.updateCareer(req);		
	}

}
