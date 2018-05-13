package com.audiance.tvprediction.dao;

import java.util.List;

import com.audiance.tvprediction.model.User;

public interface UserDao {

	User findByUserName(String username);

	List<User> findAll();

	void save(User arg0);

	void delete(User arg0);

	User findById(Long arg0);

}
