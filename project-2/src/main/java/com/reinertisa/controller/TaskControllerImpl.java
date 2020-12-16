package com.reinertisa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.reinertisa.service.TaskService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController("taskController")
public class TaskControllerImpl implements TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("api/tasks/add")
	public String addTask(HttpServletRequest req) {
		return taskService.addTask(req);
	}

	@PutMapping("api/tasks/update")
	public String updateTask(HttpServletRequest req) {
		System.out.println("In updateTask");
		return taskService.updateTask(req);
	}

//	@GetMapping("api/tasks/all")
//	public String getAllTasks() {
//		System.out.println("In getAllTasks");
//		return taskService.getAllTasks();
//	}
	
	@PostMapping("api/tasks/all")
	public String getAllTasks(HttpServletRequest req) {
		System.out.println("In getAllTasks");
		return taskService.getAllTasks(req);
	}

	@PostMapping("api/tasks/getTask")
	public  @ResponseBody String getTask(HttpServletRequest req) {	
		System.out.println("In getTask");
		return taskService.getTask(req);
	}

	@GetMapping("api/tasks/employee")
	public String getTaskByEmployee(HttpServletRequest req) {
		System.out.println("In get Task by Employee");
		
		return taskService.getTaskByEmployee(req);
	}

	@GetMapping("api/tasks/manager")
	public String getTaskByManager(HttpServletRequest req) {
		System.out.println("In getTask by Manager");
		return taskService.getTaskByManager(req);
	}
	
}
