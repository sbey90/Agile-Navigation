package com.reinertisa.repository;

import java.util.List;

import javax.persistence.TypedQuery;

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

	public User findByUsername(String username) {
		try {
			return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.like("username", username)) // this should be the PROPERTY name of the class
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

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		
		try {
			
			String sql = "from User WHERE username= : username AND password= : password";
			
			TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(sql, User.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			
			return query.getSingleResult();
			
		} catch (Exception e) {
			logger.debug(e);
			return null;
		}
	}
	
	
	
	
	
}
