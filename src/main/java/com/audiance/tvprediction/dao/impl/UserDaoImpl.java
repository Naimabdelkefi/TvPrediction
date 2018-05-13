package com.audiance.tvprediction.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.audiance.tvprediction.dao.UserDao;
import com.audiance.tvprediction.model.User;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = getSessionFactory().getCurrentSession().createQuery("from User where username=?")
				.setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	public List<User> findAll() {
		return getSessionFactory().getCurrentSession().createQuery("from User").list();
	}

	public void delete(User arg0) {
		getSessionFactory().getCurrentSession().delete(arg0);
	}

	public User findById(Long arg0) {
		return (User) getSessionFactory().getCurrentSession().get(User.class, arg0);
	}

	public void save(User arg0) {
		getSessionFactory().getCurrentSession().save(arg0);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
