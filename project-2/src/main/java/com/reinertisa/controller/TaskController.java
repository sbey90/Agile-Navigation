package com.reinertisa.controller;

import javax.servlet.http.HttpServletRequest;

public interface TaskController {
	
	public String addTask(HttpServletRequest req);
	public String updateTask(HttpServletRequest req);
	public String assignTask(HttpServletRequest req);
	public String getAllTasks();
	public String getTask(HttpServletRequest req);
	
}
