package com.reinertisa.service;

import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;

public interface TaskService {
	
	
	public boolean addTask(Task task);
	
	public boolean createTaskStatusTable(TaskStatus status);
	public boolean createTaskPriorityTable(TaskPriority priority);
	public boolean createTaskCategoryTable(TaskCategory category);
}
