package com.reinertisa.service;

import javax.servlet.http.HttpServletRequest;

import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;

public interface TaskService {
	
	
	public boolean addTask(Task task);
	
	public boolean createTaskStatusTable(TaskStatus status);
	public boolean createTaskPriorityTable(TaskPriority priority);
	public boolean createTaskCategoryTable(TaskCategory category);
	
	public String addTask(HttpServletRequest req);
	public String updateTask(HttpServletRequest req);
	public String assignTask(HttpServletRequest req);
	public String getAllTasks();
	public String getTask(HttpServletRequest req);
}
