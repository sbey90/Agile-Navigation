package com.reinertisa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="task_priority")
public class TaskPriority {
	
	@Id
	@Column(name="priority_id")
	private int priorityId;
	private String priority;
	
	public TaskPriority() {
		// TODO Auto-generated constructor stub
	}
	

	public TaskPriority(int priorityId) {
	
		this.priorityId = priorityId;
	}


	public TaskPriority(String priority) {
	
		this.priority = priority;
	}


	public TaskPriority(int priorityId, String priority) {
	
		this.priorityId = priorityId;
		this.priority = priority;
	}


	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}


	@Override
	public String toString() {
		return "TaskPriority [priorityId=" + priorityId + ", priority=" + priority + "]";
	}

}
