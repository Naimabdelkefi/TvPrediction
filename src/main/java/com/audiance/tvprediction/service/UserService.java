package com.audiance.tvprediction.service;

import java.util.List;

import com.audiance.tvprediction.model.User;

public interface UserService {

	boolean LoggedIn(User user);

	public User getUserByUsername(String username);

	List<User> getAllUsers();

	void saveUser(User arg0);

	void deleteUser(User arg0);

	User getUserById(Long arg0);
}
