package com.reinertisa.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;
import com.reinertisa.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl() {
		logger.trace("Injection using Autowired Hero Repository in UserServiceImpl");
	}

	@Override
	public String signup(HttpServletRequest req) {

		System.out.println("Hello1");
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		try {

			System.out.println("Hello2");
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			String username = rootobj.get("username").getAsString();
			String password = rootobj.get("password").getAsString();
			String firstName = rootobj.get("firstName").getAsString();
			String lastName = rootobj.get("lastName").getAsString();
			String email = rootobj.get("email").getAsString();
			LocalDateTime hireDate = LocalDateTime.now();
			String role = rootobj.get("role").getAsString();

			System.out.println("Hello3");
			User newUser = new User(username, password, firstName, lastName, email, hireDate, new UserRole(1, role));

			System.out.println("New User: " + newUser);
			
			userRepository.save(newUser);
			User user = getUserByUsername(username);

			System.out.println("User: " + user);
			
			params.addProperty("userId", user.getUserId());
			params.addProperty("username", user.getUsername());
			params.addProperty("password", user.getPassword());
			params.addProperty("firstName", user.getFirstName());
			params.addProperty("lastName", user.getLastName());
			params.addProperty("email", user.getEmail());
			params.addProperty("role", user.getRole().getRole());

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String hireDateFormatted = user.getHireDate().format(formatter);
			params.addProperty("hireDate", hireDateFormatted);

			String json = gson.toJson(params);
			return json;

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			return json;
		}
	}

	@Override
	public String getAllUsers() {
		
		
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params1 = new JsonObject();
		String json = "";
		
		try {
			
			List<User> users = userRepository.findAll();
			
			if (users == null || users.size() == 0) {
				params1.addProperty("status", "no record");
				json = gson.toJson(params1);
			} else {

				JsonArray jobj = new JsonArray();
				for (User user : users) {
					JsonObject params = new JsonObject();

					params.addProperty("userId", user.getUserId());
					params.addProperty("username", user.getUsername());
					params.addProperty("password", user.getPassword());
					params.addProperty("firstName", user.getFirstName());
					params.addProperty("lastName", user.getLastName());
					params.addProperty("email", user.getEmail());
					params.addProperty("role", user.getRole().getRole());

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					String hireDateFormatted = user.getHireDate().format(formatter);
					params.addProperty("hireDate", hireDateFormatted);
				
					jobj.add(params);
				}

				json = jobj.toString();
			}
			
		} catch (Exception e) {
			params1.addProperty("status", "process failed");
			json = gson.toJson(params1);
		}
		
		return json;
		
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean createRoleTable(UserRole role) {
		userRepository.save(role);
		return false;
	}

	@Override
	public String signin(HttpServletRequest req) {

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json = "";

		try {

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject jsonobj = root.getAsJsonObject();

			String username = jsonobj.get("username").getAsString();
			String password = jsonobj.get("password").getAsString();

			User user = userRepository.findUserByUsernameAndPassword(username, password);

			if (user != null) {

//				HttpSession session = req.getSession();
//				session.setAttribute("userId", user.getUserId());			
				json = gson.toJson(user);			

			} else {
				params.addProperty("status", "process failed");
				json = gson.toJson(params);

			}

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			json = gson.toJson(params);
		}
		
		return json;
	}

	@Override
	public String signout(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String forgotPassword(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
}