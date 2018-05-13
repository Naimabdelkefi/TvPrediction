package com.audiance.tvprediction.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;

import com.audiance.tvprediction.model.AudienceData;

public class ChartViewModelImpl implements ChartViewModel {

	CategoryModel categoryModel;

	private String dataType = "";

	private List<AudienceData> audienceDatas;

	public ChartViewModelImpl() {
		categoryModel = new DefaultCategoryModel();
		audienceDatas = new ArrayList<AudienceData>();
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public CategoryModel getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(CategoryModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	public List<AudienceData> getAudienceDatas() {
		return audienceDatas;
	}

	public void setAudienceDatas(List<AudienceData> audienceDatas) {
		this.audienceDatas = audienceDatas;
	}

	public void applyEmission() {
		for (AudienceData audienceData : audienceDatas)
			getCategoryModel().setValue(audienceData.getEmission(), audienceData.getDate(), audienceData.getRatio());
	}

	public void applyDate() {
		for (AudienceData audienceData : audienceDatas)
			getCategoryModel().setValue(audienceData.getEmission(), audienceData.getChaine(), audienceData.getRatio());
	}

	public void applyChaine() {
		for (AudienceData audienceData : audienceDatas)
			getCategoryModel().setValue(audienceData.getEmission(), audienceData.getDate(), audienceData.getRatio());

	}

}
