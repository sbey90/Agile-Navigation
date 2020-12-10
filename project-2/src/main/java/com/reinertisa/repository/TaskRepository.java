package com.reinertisa.repository;

import java.util.List;

import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;
import com.reinertisa.model.User;

public interface TaskRepository {

	int save(Task task);
	void save(TaskStatus status);
	void save(TaskPriority priority);
	void save(TaskCategory category);
	
	List<Task> findAll();
	boolean update(Task task);
	List<Task> findByEmployee(int id);
	List<Task> findByManager(int id);
}
