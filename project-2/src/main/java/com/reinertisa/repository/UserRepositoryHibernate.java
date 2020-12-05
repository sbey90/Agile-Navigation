package com.reinertisa.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;

@Repository("userRepository")
@Transactional
public class UserRepositoryHibernate implements UserRepository{

	private static Logger logger = Logger.getLogger(UserRepositoryHibernate.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserRepositoryHibernate() {
		logger.trace("Injection session factory bean");
	}
	
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
		
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}

	public User findByName(String name) {
		try {
			return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.like("name", name)) // this should be the PROPERTY name of the class
					.list()
					.get(0);
		} catch (IndexOutOfBoundsException e) { 
			logger.debug(e);
			return null;
		}
	}

	@Override
	public void save(UserRole role) {
		sessionFactory.getCurrentSession().save(role);
		
	}
	
	
}
