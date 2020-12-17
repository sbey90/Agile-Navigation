package com.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;
import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;
import com.reinertisa.repository.TaskRepository;
import com.reinertisa.service.TaskServiceImpl;
import com.reinertisa.service.UserService;


@SuppressWarnings("deprecation")
@RunWith(JUnit4ClassRunner.class)
public class TaskServiceTests {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	DateTimeFormatter reqFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	@Mock
	private TaskRepository taskRepository;
	
	@Mock
	private UserService userService;
	
	@Autowired
	@InjectMocks
	private TaskServiceImpl taskService;
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	private Task getTask(int id) {
		return new Task(id, "project-2", new TaskCategory(1, "Planning"), 
				new TaskStatus(1, "Pending"), "Thank you",
				new TaskPriority(1, "Do Now"), getUser(1), LocalDateTime.now(), 
				getUser(2), LocalDateTime.now(), LocalDateTime.now());
	}
	
	private User getUser(int id) {
		return new User(id, "username", "password", "first", "last", "email", LocalDateTime.now(), new UserRole(4, "Software Manager"));
	}
	
	
	
	private JsonObject getParams(Task t) {
		JsonObject params = new JsonObject();
		
		params.addProperty("taskId", t.getTaskId());
		params.addProperty("taskName", t.getTaskName());
		params.addProperty("taskCategory", t.getTaskCategory().getCategory());
		params.addProperty("taskStatus", t.getTaskStatus().getStatus());
		params.addProperty("description", t.getDescription());
		params.addProperty("taskPriority", t.getTaskPriority().getPriority());
		if (t.getEmployee().getUsername() != null) {
			params.addProperty("employee", t.getEmployee().getFirstName() + " " + t.getEmployee().getLastName());
		} else {
			params.addProperty("employee", "null null");
		}
		
		if (t.getTaskCompletedDate() == null) {
			params.addProperty("taskCompletedDate", "null");
		} else {
			params.addProperty("taskCompletedDate", t.getTaskCompletedDate().format(formatter));
		}
		
		params.addProperty("manager", t.getManager().getUsername());
		params.addProperty("taskSubmittedDate", t.getTaskSubmittedDate().format(formatter));
		if (t.getTaskDueDate() != null ) {
			params.addProperty("taskDueDate", t.getTaskDueDate().format(formatter));
		} else {
			params.addProperty("taskDueDate", "null");
		}
		
		return params;
		
		
	}
	
	private JsonObject getJson(Task t) {
		JsonObject params = new JsonObject();
		
		params.addProperty("taskId", t.getTaskId());
		params.addProperty("taskName", t.getTaskName());
		params.addProperty("taskCategory", t.getTaskCategory().getCategoryId());
		params.addProperty("taskStatus", t.getTaskStatus().getStatusId());
		params.addProperty("description", t.getDescription());
		params.addProperty("taskPriority", t.getTaskPriority().getPriorityId());
		if (t.getEmployee().getUsername() != null) {
			params.addProperty("employee", t.getEmployee().getFirstName() + " " + t.getEmployee().getLastName());
		} else {
			params.addProperty("employee", "null null");
		}
		
		if (t.getTaskCompletedDate() == null) {
			params.addProperty("taskCompletedDate", "null");
		} else {
			params.addProperty("taskCompletedDate", t.getTaskCompletedDate().format(reqFormatter));
		}
		
		params.addProperty("managerId", t.getManager().getUserId());
		params.addProperty("taskSubmittedDate", t.getTaskSubmittedDate().format(reqFormatter));
		if (t.getTaskDueDate() != null ) {
			params.addProperty("taskDueDate", t.getTaskDueDate().format(reqFormatter));
		} else {
			params.addProperty("taskDueDate", "null");
		}
		
		return params;
		
		
	}
	
	@Test
	public void getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(getTask(1));
		tasks.add(getTask(2));
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("application/json");
		request.setContent("{\"userId\":1}".getBytes());
		Mockito.when(userService.getUser(1)).thenReturn(getUser(1));
		Mockito.when(taskRepository.findAll()).thenReturn(tasks);
		JsonArray jobj = new JsonArray();
		for (Task t: tasks) {					
			jobj.add(getParams(t));
		}
		//System.out.println(jobj.toString());
		//System.out.println(taskService.getAllTasks(request));
		assertEquals(jobj.toString(), taskService.getAllTasks(request));
		
	}
	
	@Test
	public void getTasksEmployee() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(getTask(1));
		tasks.add(getTask(2));
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("application/json");
		request.setContent("{\"userId\":1}".getBytes());
		User u = getUser(1);
		u.setRole(new UserRole(1, "Analyst"));
		Mockito.when(userService.getUser(1)).thenReturn(u);
		Mockito.when(taskRepository.findByEmployee(1)).thenReturn(tasks);
		JsonArray jobj = new JsonArray();
		for (Task t: tasks) {					
			jobj.add(getParams(t));
		}
		//System.out.println(jobj.toString());
		//System.out.println(taskService.getAllTasks(request));
		assertEquals(jobj.toString(), taskService.getAllTasks(request));
		
	}
	
	@Test
	public void getTasksFail() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(getTask(1));
		tasks.add(getTask(2));
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("application/json");
		request.setContent("{\"userId\":1}".getBytes());
		Mockito.when(userService.getUser(1)).thenReturn(getUser(1));
		Mockito.when(taskRepository.findAll()).thenReturn(null);
		JsonArray jobj = new JsonArray();
		for (Task t: tasks) {					
			jobj.add(getParams(t));
		}
		System.out.println(taskService.getAllTasks(request));
		assertEquals("{\"status\":\"Failed to get All tasks\"}", taskService.getAllTasks(request));
		
	}
	
	@Test
	public void getTasksException() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(getTask(1));
		tasks.add(getTask(2));
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("application/json");
		request.setContent("{\"userId\":1}".getBytes());
		Mockito.when(userService.getUser(1)).thenReturn(getUser(1));
		Mockito.when(taskRepository.findAll()).thenThrow(new RuntimeException());
		JsonArray jobj = new JsonArray();
		for (Task t: tasks) {					
			jobj.add(getParams(t));
		}
		System.out.println(taskService.getAllTasks(request));
		assertEquals("{\"status\":\"Failed to get All tasks\"}", taskService.getAllTasks(request));
		
	}
	
	@Test
	public void updateTaskSuccess() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("application/json");
		Task t = getTask(0);
		System.out.println(getJson(t).toString());
		request.setContent(getJson(t).toString().getBytes());
		Mockito.when(userService.getUser("first", "last")).thenReturn(getUser(1));
		Mockito.when(taskRepository.update(1,1)).thenReturn(true);
		assertEquals("{\"status\":\"Task updated successfully\"}", taskService.updateTask(request));
	}
	
	@Test
	public void updateTaskException() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("application/json");
		Task t = getTask(0);
		System.out.println(getJson(t).toString());
		request.setContent("{\"taskId\":1}".toString().getBytes());
		Mockito.when(userService.getUser("first", "last")).thenThrow(new RuntimeException());
		Mockito.when(taskRepository.update(1,1)).thenThrow(new RuntimeException());
		assertEquals("{\"status\":\"Failed to update task\"}", taskService.updateTask(request));
	}
	
	
}
