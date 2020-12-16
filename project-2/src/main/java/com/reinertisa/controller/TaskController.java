package com.reinertisa.controller;

import javax.servlet.http.HttpServletRequest;

public interface TaskController {
	
	public String addTask(HttpServletRequest req);
	public String updateTask(HttpServletRequest req);
	public String getAllTasks(HttpServletRequest req);
	public String getTask(HttpServletRequest req);
	public String getTaskByEmployee(HttpServletRequest req);
	public String getTaskByManager(HttpServletRequest req);
	
}
