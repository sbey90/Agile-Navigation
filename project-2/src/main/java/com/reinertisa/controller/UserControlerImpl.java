package com.reinertisa.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;
import com.reinertisa.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller("userController")
public class UserControlerImpl implements UserController{
	
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
	
	@GetMapping("api/signout")
	public String signout(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping("api/forgotPassword")
	public String forgotPassword(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping("api/getUser")
	public @ResponseBody User getUser(User user, HttpServletRequest req) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping("api/getAllUsers")
	public @ResponseBody String getAllUsers() {
		return userService.getAllUsers();
	}



}
