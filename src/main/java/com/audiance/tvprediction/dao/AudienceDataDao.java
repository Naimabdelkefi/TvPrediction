package com.audiance.tvprediction.dao;

import java.util.List;

import com.audiance.tvprediction.model.AudienceData;

public interface AudienceDataDao {


	List<AudienceData> findAll();

	void save(AudienceData arg0);

	void delete(AudienceData arg0);

	AudienceData findById(Long arg0);
	
	List<AudienceData> findBy(String type,String pram);
	List<AudienceData> findByDateIntAndChaine(String date1,String date2,String chiane);
	List<AudienceData> findByDateInt(String date1,String date2);

}
