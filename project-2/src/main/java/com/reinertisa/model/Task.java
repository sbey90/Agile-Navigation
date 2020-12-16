package com.reinertisa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private int taskId;
	
	@Column(name = "task_name")
	private String taskName;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private TaskCategory taskCategory;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private TaskStatus taskStatus; 
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="priority_id")
	private TaskPriority taskPriority;
	
	@OneToOne
	@JoinColumn(name="employee_id")
	private User employee;
	
	@Column(name = "task_completed_date")
	private LocalDateTime taskCompletedDate;
	
	@OneToOne
	@JoinColumn(name = "manager_id")
	private User manager;
	
	@Column(name = "task_submitted_date")
	private LocalDateTime taskSubmittedDate;
	
	@Column(name = "due_date")
	private LocalDateTime taskDueDate;

	public Task() {
		
	}	

	public Task(String taskName, TaskCategory taskCategory, TaskStatus taskStatus, String description,
			TaskPriority taskPriority, User employee, LocalDateTime taskCompletedDate, User manager,
			LocalDateTime taskSubmittedDate, LocalDateTime taskDueDate) {
		super();
		this.taskName = taskName;
		this.taskCategory = taskCategory;
		this.taskStatus = taskStatus;
		this.description = description;
		this.taskPriority = taskPriority;
		this.employee = employee;
		this.taskCompletedDate = taskCompletedDate;
		this.manager = manager;
		this.taskSubmittedDate = taskSubmittedDate;
		this.taskDueDate = taskDueDate;
	}



	public Task(int taskId, String taskName, TaskCategory taskCategory, TaskStatus taskStatus, String description,
			TaskPriority taskPriority, User employee, LocalDateTime taskCompletedDate, User manager,
			LocalDateTime taskSubmittedDate, LocalDateTime taskDueDate) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskCategory = taskCategory;
		this.taskStatus = taskStatus;
		this.description = description;
		this.taskPriority = taskPriority;
		this.employee = employee;
		this.taskCompletedDate = taskCompletedDate;
		this.manager = manager;
		this.taskSubmittedDate = taskSubmittedDate;
		this.taskDueDate = taskDueDate;
	}
	
	

	public Task(String taskName, TaskCategory taskCategory, TaskStatus taskStatus, String description,
			TaskPriority taskPriority, User employee, User manager, LocalDateTime taskSubmittedDate,
			LocalDateTime taskDueDate) {
		
		this.taskName = taskName;
		this.taskCategory = taskCategory;
		this.taskStatus = taskStatus;
		this.description = description;
		this.taskPriority = taskPriority;
		this.employee = employee;
		this.manager = manager;
		this.taskSubmittedDate = taskSubmittedDate;
		this.taskDueDate = taskDueDate;
	}
	
	

	public Task(int taskId, TaskStatus taskStatus, LocalDateTime taskCompletedDate ) {
		super();
		this.taskId = taskId;
		this.taskStatus = taskStatus;
		this.taskCompletedDate = taskCompletedDate;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public TaskCategory getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(TaskCategory taskCategory) {
		this.taskCategory = taskCategory;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskPriority getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(TaskPriority taskPriority) {
		this.taskPriority = taskPriority;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public LocalDateTime getTaskCompletedDate() {
		return taskCompletedDate;
	}

	public void setTaskCompletedDate(LocalDateTime taskCompletedDate) {
		this.taskCompletedDate = taskCompletedDate;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public LocalDateTime getTaskSubmittedDate() {
		return taskSubmittedDate;
	}

	public void setTaskSubmittedDate(LocalDateTime taskSubmittedDate) {
		this.taskSubmittedDate = taskSubmittedDate;
	}

	public LocalDateTime getTaskDueDate() {
		return taskDueDate;
	}

	public void setTaskDueDate(LocalDateTime taskDueDate) {
		this.taskDueDate = taskDueDate;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskCategory=" + taskCategory + ", taskStatus="
				+ taskStatus + ", description=" + description + ", taskPriority=" + taskPriority + ", employee="
				+ employee + ", taskCompletedDate=" + taskCompletedDate + ", manager=" + manager
				+ ", taskSubmittedDate=" + taskSubmittedDate + ", taskDueDate=" + taskDueDate + "]";
	}
	
	
	

}
