package com.reinertisa.repository;

import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;

public interface TaskRepository {

	void save(Task task);
	void save(TaskStatus status);
	void save(TaskPriority priority);
	void save(TaskCategory category);
}
