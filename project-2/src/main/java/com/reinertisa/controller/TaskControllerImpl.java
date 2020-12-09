package com.reinertisa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinertisa.service.TaskService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController("taskController")
public class TaskControllerImpl implements TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("api/tasks/add")
	public String addTask(HttpServletRequest req) {
		System.out.println("In addTask");
		return taskService.addTask(req);
	}

	@PostMapping("api/tasks/update")
	public String updateTask(HttpServletRequest req) {
		System.out.println("In updateTask");
		return null;
	}

	@PostMapping("api/tasks/assign")
	public String assignTask(HttpServletRequest req) {
		System.out.println("In assignTask");
		return null;
	}

	@GetMapping("api/tasks/all")
	public String getAllTasks() {
		System.out.println("In getAllTasks");
		return taskService.getAllTasks();
	}

	@PostMapping("api/tasks/getTask")
	public String getTask(HttpServletRequest req) {
		System.out.println("In getTask");
		return null;
	}

}
