package com.reinertisa.service;

import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;

import javax.servlet.http.HttpServletRequest;

public interface TaskService {


    public boolean addTask(Task task);

    public boolean createTaskStatusTable(TaskStatus status);

    public boolean createTaskPriorityTable(TaskPriority priority);

    public boolean createTaskCategoryTable(TaskCategory category);

    public String addTask(HttpServletRequest req);

    public String updateTask(HttpServletRequest req);

    public String getAllTasks(HttpServletRequest req);

    public String getTask(HttpServletRequest req);

    public String getTaskByEmployee(HttpServletRequest req);

    public String getTaskByManager(HttpServletRequest req);
}
