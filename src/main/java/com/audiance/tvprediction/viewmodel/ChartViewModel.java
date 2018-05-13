package com.audiance.tvprediction.viewmodel;

import java.util.List;

import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.ChartsModel;

import com.audiance.tvprediction.model.AudienceData;

public interface ChartViewModel {

	public String getDataType();

	public void setDataType(String dataType);

	public ChartsModel getCategoryModel();

	public void setCategoryModel(CategoryModel categoryModel);

	public List<AudienceData> getAudienceDatas();

	public void setAudienceDatas(List<AudienceData> audienceDatas);

	public void applyEmission();

	public void applyDate();

	public void applyChaine();
}
