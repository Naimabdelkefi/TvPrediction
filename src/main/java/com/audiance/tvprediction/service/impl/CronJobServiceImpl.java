package com.audiance.tvprediction.service.impl;

import java.util.List;

import com.audiance.tvprediction.dao.CronJobDAO;
import com.audiance.tvprediction.model.CronJob;
import com.audiance.tvprediction.service.CronJobService;

public class CronJobServiceImpl implements CronJobService {
	private CronJobDAO cronJobDao;

	public List<CronJob> getAllCronJob() {
		return getCronJobDao().findAll();
	}

	public void saveCronJob(CronJob arg0) {
		getCronJobDao().save(arg0);

	}

	public void deleteCronJobRaw(CronJob arg0) {
		getCronJobDao().delete(arg0);

	}

	public CronJob getCronJobById(Long arg0) {
		return getCronJobDao().findById(arg0);
	}

	public CronJob getDataByIdAdmin(long arg0) {
		return getCronJobDao().findByIdAdmin(arg0);
	}

	public CronJobDAO getCronJobDao() {
		return cronJobDao;
	}

	public void setCronJobDao(CronJobDAO cronJobDao) {
		this.cronJobDao = cronJobDao;
	}

	public CronJob getCronjobByName(String name) {
		return getCronJobDao().findByCronjobName(name);
	}

}
