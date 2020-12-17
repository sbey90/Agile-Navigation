package com.reinertisa.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.reinertisa.util.SendEmail;

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

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		try {

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
			double salary = 5000D;

			if (!userRepository.isAvailableUsername(username)) {
				params.addProperty("status", "username not available");
				String json = gson.toJson(params);
				return json;
			}

			if (!userRepository.isAvailableEmail(email)) {
				params.addProperty("status", "email not available");
				String json = gson.toJson(params);
				return json;
			}

			User newUser = new User(username, password, firstName, lastName, email, salary, hireDate, new UserRole(1, role));

			userRepository.save(newUser);
			User user = getUserByUsername(username);

			params.addProperty("userId", user.getUserId());
			params.addProperty("username", user.getUsername());
			params.addProperty("password", user.getPassword());
			params.addProperty("firstName", user.getFirstName());
			params.addProperty("lastName", user.getLastName());
			params.addProperty("email", user.getEmail());
			params.addProperty("salary", user.getSalary());
			params.addProperty("role", user.getRole().getRole());

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String hireDateFormatted = user.getHireDate().format(formatter);
			params.addProperty("hireDate", hireDateFormatted);
			params.addProperty("status", "account created");

			String json = gson.toJson(params);
			return json;

		} catch (Exception e) {
			params.addProperty("status", "account not created");
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
					params.addProperty("salary", user.getSalary());

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

				params.addProperty("userId", user.getUserId());
				params.addProperty("username", user.getUsername());
				params.addProperty("firstName", user.getFirstName());
				params.addProperty("lastName", user.getLastName());
				params.addProperty("role", user.getRole().getRole());
				params.addProperty("status", "signin success");

				json = gson.toJson(params);

			} else {
				params.addProperty("status", "signin failed");
				json = gson.toJson(params);
			}

		} catch (Exception e) {
			params.addProperty("status", "signin failed");
			json = gson.toJson(params);
		}

		return json;
	}

	@Override
	public String signout(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("static-access")
	@Override
	public String forgotPass(HttpServletRequest req) {

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json = "";

		try {

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			String toEmail = rootobj.get("email").getAsString();

			User user = userRepository.findUserByEmail(toEmail);

			if (user != null) {

				SendEmail email = new SendEmail();
				if (email.sendEmail(user)) {
					params.addProperty("status", "email sent successfully");
					json = gson.toJson(params);
				} else {
					params.addProperty("status", "email not sent");
					json = gson.toJson(params);
				}

			} else {
				params.addProperty("status", "invalid email");
				json = gson.toJson(params);
			}

		} catch (Exception e) {
			params.addProperty("status", "email not sent");
			json = gson.toJson(params);
		}

		return json;
	}

	@Override
	public String getUser(HttpServletRequest req) {

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json = "";
		
		try {

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			int userId = rootobj.get("userId").getAsInt();
			User user = userRepository.findUserByUserId(userId);
			
			if (user == null) {
				params.addProperty("status", "no user found");
				json = gson.toJson(params);
			} else {

				params.addProperty("userId", user.getUserId());
				params.addProperty("username", user.getUsername());
				params.addProperty("password", user.getPassword());
				params.addProperty("firstName", user.getFirstName());
				params.addProperty("lastName", user.getLastName());
				params.addProperty("email", user.getEmail());
				params.addProperty("role", user.getRole().getRole());
				params.addProperty("salary", user.getSalary());

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String hireDateFormatted = user.getHireDate().format(formatter);
				params.addProperty("hireDate", hireDateFormatted);

				json = gson.toJson(params);
			}

		} catch (Exception e) {
			params.addProperty("status", "no user found");
			json = gson.toJson(params);
		}

		return json;

	}

	@Override
	public String updateUser(HttpServletRequest req) {	
		
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json = "";

		
		try {
			
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();
			
			int userId = rootobj.get("userId").getAsInt();
			String username = rootobj.get("username").getAsString();
			String password = rootobj.get("password").getAsString();
			String firstName = rootobj.get("firstName").getAsString();
			String lastName = rootobj.get("lastName").getAsString();
			String email = rootobj.get("email").getAsString();
			
			User user = userRepository.findUserByUserId(userId);
			
			System.out.println(user);

			if (!userRepository.isAvailableUsername(username)) {
				if(!user.getUsername().equals(username)){
					params.addProperty("status", "username not available");
					json = gson.toJson(params);
					return json;
				}
			}
			
			if(!userRepository.isAvailableEmail(email)) {
				if(!user.getEmail().equals(email)){
					params.addProperty("status", "email not available");
					json = gson.toJson(params);
					return json;
				}
			}
			
			User updateUser = new User(userId, username, password, firstName, lastName, email);
			
			if(userRepository.updateUser(updateUser)) {
				params.addProperty("status", "profile updated successfully");
				json = gson.toJson(params);
			} else {
				params.addProperty("status", "profile not updated");
				json = gson.toJson(params);
			}		
			
			
			
		} catch (Exception e) {
			params.addProperty("status", "profile not updated");
			json = gson.toJson(params);
		}
		
		return json;
		
	}
	
	
	
	@Override
	public String updateCareer(HttpServletRequest req) {
		
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json = "";

		
		try {
			
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();
			
			int userId = rootobj.get("userId").getAsInt();
			int roleId = rootobj.get("role").getAsInt();
			
			User user = userRepository.findUserByUserId(userId);
			
			

			
			if(userRepository.updateCareer(userId, user.getSalary() * 1.20 , roleId)) {
				params.addProperty("status", "Career updated successfully");
				json = gson.toJson(params);
			} else {
				params.addProperty("status", "Career not updated");
				json = gson.toJson(params);
			}		
			
			
			
		} catch (Exception e) {
			params.addProperty("status", "Career not updated");
			json = gson.toJson(params);
		}
		
		return json;
	}
	
	
	

	@Override
	public String getAllUsers(HttpServletRequest req) {
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params1 = new JsonObject();
		String json = "";

		try {

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();
			
			String username = rootobj.get("username").getAsString();
			List<User> users = userRepository.findAllByUsername(username);

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
					params.addProperty("salary", user.getSalary());

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
	public User getUser(String firstName, String lastName) {
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public User getUser(int userId) {
		return userRepository.findUserByUserId(userId);
	}



}