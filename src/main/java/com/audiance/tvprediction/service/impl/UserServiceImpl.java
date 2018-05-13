package com.audiance.tvprediction.service.impl;

import java.util.List;

import com.audiance.tvprediction.dao.UserDao;
import com.audiance.tvprediction.model.User;
import com.audiance.tvprediction.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public User getUserByUsername(final String username) {
		User user = userDao.findByUserName(username);
		return user;
	}

	public boolean LoggedIn(User user) {
		User userByUsername = getUserByUsername(user.getUsername());
		return (userByUsername == null) ? false
				: (userByUsername.getPassword().equals(user.getPassword())) ? true : false;
	}

	public List<User> getAllUsers() {
		return getUserDao().findAll();
	}

	public void saveUser(User arg0) {
		getUserDao().save(arg0);
	}

	public void deleteUser(User arg0) {
		getUserDao().delete(arg0);
	}

	public User getUserById(Long arg0) {
		return getUserDao().findById(arg0);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
