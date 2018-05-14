package com.audiance.tvprediction.service;

import java.util.List;

import com.audiance.tvprediction.model.CronJob;

public interface CronJobService {

	List<CronJob> getAllCronJob();

	void saveCronJob(CronJob arg0);

	void deleteCronJobRaw(CronJob arg0);

	public CronJob getCronJobById(Long arg0);

	public CronJob getDataByIdAdmin(long arg0);

	CronJob getCronjobByName(String name);
}
