package com.reinertisa.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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

	@Override
	public boolean isAvailableEmail(String email) {

		try {
			
			String sql = "from User WHERE email= : email";
			
			TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(sql, User.class);
			query.setParameter("email", email);
			
			try {
				query.getSingleResult();	
				return false;
			} catch (NoResultException e) {
				return true;
			}
			
			
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return true;		
	}

	@Override
	public boolean isAvailableUsername(String username) {
		try {
			
			String sql = "from User WHERE username= :username";
			
			TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(sql, User.class);
			query.setParameter("username", username);
			
			try {
				query.getSingleResult();	
				return false;
			} catch (NoResultException e) {
				return true;
			}			
			
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return true;	
	}

	@Override
	public User findUserByEmail(String email) {
		
		try {			
			
			String sql = "from User WHERE email= :email";
			
			TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(sql, User.class);
			query.setParameter("email", email);
			
			try {
				User user = query.getSingleResult();	
				return user;
			} catch (NoResultException e) {
				return null;
			}
			
		} catch (Exception e) {
			logger.debug(e);
		}			
		return null;
	}

	@Override
	public User findUserByUserId(int userId) {
		
		try {			
			
			String sql = "from User WHERE userId= : userId";
			
			TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(sql, User.class);
			query.setParameter("userId", userId);
			
			User user = query.getSingleResult();
			
			return user;
			
		} catch (Exception e) {
			logger.debug(e);
		}	
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateUser(User user) {

		
		try {
		
			
			String sql = "UPDATE User SET username= :username, password= :password, firstName= :firstName"
					+ ", lastName= :lastName, email= :email WHERE userId= :userId";					
			
			Query<User> query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("userId", user.getUserId());
			query.setParameter("username", user.getUsername());
			query.setParameter("password", user.getPassword());
			query.setParameter("firstName", user.getFirstName());
			query.setParameter("lastName", user.getLastName());
			query.setParameter("email", user.getEmail());
			
			int count = query.executeUpdate();
			
			if(count > 0)
				return true;
			
		} catch (Exception e) {
			logger.debug(e);
		}	
		
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean updateCareer(int userId, double salary, int roleId) {
				
		try {
		
			
			String sql = "UPDATE users SET salary= :salary, role_role_id= :roleId WHERE user_id= :userId";					
			
			Query<User> query = sessionFactory.getCurrentSession().createNativeQuery(sql);
			query.setParameter("userId", userId);
			query.setParameter("salary", salary);
			query.setParameter("roleId", roleId);
					
			int count = query.executeUpdate();
			
			if(count > 0)
				return true;
			
		} catch (Exception e) {
			logger.debug(e);
		}	
		
		return false;
		
		
	}	

	@Override
	public List<User> findAllByUsername(String username) {
		try {			
			
			String sql = "from User WHERE username LIKE :searchKey";
			
			TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(sql, User.class);
			query.setParameter("searchKey", "%" + username + "%");
			
			List<User> users = query.getResultList();
			
			return users;
			
		} catch (Exception e) {
			logger.debug(e);
		}	
		
		return null;
	}

	@Override
	public User findByFirstNameAndLastName(String firstName, String lastName) {
		try {			
			
			String sql = "from User WHERE firstName= : firstName AND lastName= : lastName";
			
			TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(sql, User.class);
			query.setParameter("firstName", firstName);
			query.setParameter("lastName", lastName);
			
			User user = query.getSingleResult();
			
			return user;
			
		} catch (Exception e) {
			logger.debug(e);
		}	
		
		return null;
	}


	
	
}
