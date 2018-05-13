package com.audiance.tvprediction.dao;

import java.util.List;

import com.audiance.tvprediction.model.CronJob;;

public interface CronJobDAO {
	CronJob findByCronjobName(String name);

	List<CronJob> findAll();

	void save(CronJob arg0);

	void delete(CronJob arg0);

	CronJob findById(Long arg0);
	CronJob findByIdAdmin(Long arg0);

}
