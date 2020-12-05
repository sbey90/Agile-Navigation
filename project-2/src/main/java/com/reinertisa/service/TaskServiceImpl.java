package com.reinertisa.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;
import com.reinertisa.repository.TaskRepository;

@Service("taskService")
public class TaskServiceImpl implements TaskService{

	private static Logger logger = Logger.getLogger(TaskServiceImpl.class);
	
	@Autowired
	private TaskRepository taskRepository;
	
	public TaskServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean addTask(Task task) {
		taskRepository.save(task);
		return task.getTaskId() != 0;
	}
	
	@Override
	public boolean createTaskStatusTable(TaskStatus status) {
		taskRepository.save(status);
		return status.getStatusId() != 0;
	}

	@Override
	public boolean createTaskPriorityTable(TaskPriority priority) {
		taskRepository.save(priority);
		return priority.getPriorityId() != 0;
	}

	@Override
	public boolean createTaskCategoryTable(TaskCategory category) {
		taskRepository.save(category);
		return category.getCategoryId() != 0;
	}	

}
