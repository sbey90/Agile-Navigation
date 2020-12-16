package com.reinertisa.demo;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;
import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;
import com.reinertisa.repository.UserRepository;
import com.reinertisa.service.TaskService;
import com.reinertisa.service.UserService;

public class MainDriver {


	private static Logger logger = Logger.getLogger(MainDriver.class);

	public static void main(String[] args) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		UserService userService = appContext.getBean("userService", UserService.class);
		TaskService taskService = appContext.getBean("taskService", TaskService.class);
		
		setDatabase(userService, taskService);

		
		System.out.println("Task created Successfully");
	}
	
	public static void setDatabase(UserService userService, TaskService taskService) {
		
		
		///////////////User Role Table Set Up///////////////////////
		UserRole userRole1 = new UserRole(1, "Analyst");
		UserRole userRole2 = new UserRole(2, "Software Developer");
		UserRole userRole3 = new UserRole(3, "Software Architecture");
		UserRole userRole4 = new UserRole(4, "Software Manager");
		
		userService.createRoleTable(userRole1);
		userService.createRoleTable(userRole2);
		userService.createRoleTable(userRole3);
		userService.createRoleTable(userRole4);
		System.out.println("User Role Table Created Successfully");

		///////////////Task Status Table Set Up//////////////////////
		TaskStatus status1 = new TaskStatus(1, "Pending");
		TaskStatus status2 = new TaskStatus(2, "In Progress");
		TaskStatus status3 = new TaskStatus(3, "Completed");
		
		taskService.createTaskStatusTable(status1);
		taskService.createTaskStatusTable(status2);
		taskService.createTaskStatusTable(status3);

		System.out.println("Task Status Table Created Successfully");
		
		///////////////Task Category Table Set Up////////////////////		
		TaskCategory category1= new TaskCategory(1, "Planning");
		TaskCategory category2 = new TaskCategory(2, "Analysis");
		TaskCategory category3 = new TaskCategory(3, "Design");
		TaskCategory category4 = new TaskCategory(4, "Development");
		TaskCategory category5 = new TaskCategory(5, "Testing");
		TaskCategory category6 = new TaskCategory(6, "Implementation");
		
		taskService.createTaskCategoryTable(category1);
		taskService.createTaskCategoryTable(category2);
		taskService.createTaskCategoryTable(category3);
		taskService.createTaskCategoryTable(category4);
		taskService.createTaskCategoryTable(category5);
		taskService.createTaskCategoryTable(category6);
		
		System.out.println("Task Status Table Created Successfully");
		
		///////////////Task Priority Table Set Up////////////////////		
		
		TaskPriority priority1= new TaskPriority(1, "Do Now");
		TaskPriority priority2= new TaskPriority(2, "Do Next");
		TaskPriority priority3= new TaskPriority(3, "Do Last");
		TaskPriority priority4= new TaskPriority(4, "Do Never");
		
		taskService.createTaskPriorityTable(priority1);
		taskService.createTaskPriorityTable(priority2);
		taskService.createTaskPriorityTable(priority3);
		taskService.createTaskPriorityTable(priority4);
		
		System.out.println("Task Priority Table Created Successfully");
	}


	
}
