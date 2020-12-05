package com.reinertisa.repository;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;


@Repository("taskRepository")
@Transactional
public class TaskRepositoryHibernate implements TaskRepository{
	
	private static Logger logger = Logger.getLogger(TaskRepositoryHibernate.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public TaskRepositoryHibernate() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Task task) {
		sessionFactory.getCurrentSession().save(task);		
	}

	@Override
	public void save(TaskStatus status) {
		sessionFactory.getCurrentSession().save(status);
	}

	@Override
	public void save(TaskPriority priority) {
		sessionFactory.getCurrentSession().save(priority);
	}

	@Override
	public void save(TaskCategory category) {
		sessionFactory.getCurrentSession().save(category);
	}

}
