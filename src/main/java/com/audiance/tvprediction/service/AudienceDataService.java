package com.audiance.tvprediction.service;

import java.util.List;

import com.audiance.tvprediction.model.AudienceData;;

public interface AudienceDataService {

	List<AudienceData> getAllData();

	void saveData(AudienceData arg0);

	void deleteDataRaw(AudienceData arg0);

	public AudienceData getDataById(Long arg0);
	public List<AudienceData>  getDataByChaine(String chaine);
	public List<AudienceData>  getDataByEmission(String emission);
	public  List<AudienceData>  getDataByDate(String date);
	public  List<AudienceData>  getDataByDateIntAndChaine(String date1,String date2,String chaine);
	public  List<AudienceData>  getDataByDateInt(String date1,String date2);
}
