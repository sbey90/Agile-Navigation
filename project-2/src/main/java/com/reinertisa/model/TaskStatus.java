package com.reinertisa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="task_status")
public class TaskStatus {
	
	@Id
	@Column(name="status_id")
	private int statusId;
	private String status;

	public TaskStatus() {
		// TODO Auto-generated constructor stub
	}

	public TaskStatus(String status) {
		super();
		this.status = status;
	}

	
	
	public TaskStatus(int statusId) {
		super();
		this.statusId = statusId;
	}

	public TaskStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TaskStatus [statusId=" + statusId + ", status=" + status + "]";
	}


}
