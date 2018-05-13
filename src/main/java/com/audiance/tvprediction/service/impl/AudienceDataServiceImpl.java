package com.audiance.tvprediction.service.impl;

import java.util.List;

import com.audiance.tvprediction.dao.AudienceDataDao;
import com.audiance.tvprediction.model.AudienceData;
import com.audiance.tvprediction.model.User;
import com.audiance.tvprediction.service.AudienceDataService;

public class AudienceDataServiceImpl implements AudienceDataService{
	private AudienceDataDao audienceDataDao;

	public List<AudienceData> getAllData() {
		return getAudienceDataDao().findAll();
		
	}

	public void saveData(AudienceData arg0) {
		getAudienceDataDao().save(arg0);
		
	}

	public void deleteDataRaw(AudienceData arg0) {
			getAudienceDataDao().delete(arg0);
	}

	public AudienceData getDataById(Long arg0) {
		return getAudienceDataDao().findById(arg0);
	}

	public List<AudienceData> getDataByChaine(String chaine) {
		List<AudienceData> audienceData = audienceDataDao.findBy("chaine",chaine);
		return audienceData;
	}

	public List<AudienceData>  getDataByEmission(String emission) {
		List<AudienceData>  audienceData=audienceDataDao.findBy("emission",emission);
		return audienceData;
	}

	public List<AudienceData> getDataByDate(String date) {
		List<AudienceData> audienceData=audienceDataDao.findBy("date",date);
		return audienceData;
	}

	public AudienceDataDao getAudienceDataDao() {
		return audienceDataDao;
	}

	public void setAudienceDataDao(AudienceDataDao audienceDataDao) {
		this.audienceDataDao = audienceDataDao;
	}

	public List<AudienceData> getDataByDateIntAndChaine(String date1, String date2, String chaine) {
		List<AudienceData> audienceData=audienceDataDao.findByDateIntAndChaine(date1, date2, chaine);
		return audienceData;
	}
	public List<AudienceData> getDataByDateInt(String date1, String date2) {
		List<AudienceData> audienceData=audienceDataDao.findByDateInt(date1, date2);
		return audienceData;
	}
	

}
