package com.reinertisa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_category")
public class TaskCategory {
	
	@Id
	@Column(name = "category_id")
	private int categoryId;
	private String category;
	
	public TaskCategory() {
		// TODO Auto-generated constructor stub
	}
	
	
	public TaskCategory(int categoryId) {
		super();
		this.categoryId = categoryId;
	}

	public TaskCategory(String category) {
		super();
		this.category = category;
	}

	
	public TaskCategory(int categoryId, String category) {
		super();
		this.categoryId = categoryId;
		this.category = category;
	}


	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "TaskCategory [categoryId=" + categoryId + ", category=" + category + "]";
	}
	
	

}
