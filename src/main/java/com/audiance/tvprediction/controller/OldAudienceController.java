package com.audiance.tvprediction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;

import com.audiance.tvprediction.model.AudienceData;
import com.audiance.tvprediction.model.User;
import com.audiance.tvprediction.service.AudienceDataService;
import com.audiance.tvprediction.service.UserService;
import com.audiance.tvprediction.viewmodel.ChartViewModel;

@Controller
public class OldAudienceController {

	@Autowired
	AudienceDataService audienceDataService;

	@Autowired
	ChartViewModel chartViewModel;

		@RequestMapping(value = "/oldaudiance/emission", method = RequestMethod.POST)
	public String oldemissionPOST(@RequestParam("emission") String emssion, HttpServletRequest request) {
		CategoryModel categoryModel = new DefaultCategoryModel();
		List<AudienceData> audienceDatas = audienceDataService.getDataByEmission(emssion);
		chartViewModel.getAudienceDatas().addAll(audienceDatas);
		chartViewModel.setCategoryModel(categoryModel);
		chartViewModel.setDataType("emission");
		chartViewModel.applyEmission();
		return "redirect:emission";
	}

	@RequestMapping(value = "/oldaudiance/emission", method = RequestMethod.GET)

	public String oldemissionGET(HttpServletRequest request, HttpSession session) {
		if (!chartViewModel.getDataType().equals("emission")) {
			CategoryModel categoryModel = new DefaultCategoryModel();
			List<AudienceData> audienceDatas = audienceDataService.getDataByEmission("Bones");
			chartViewModel.getAudienceDatas().clear();
			chartViewModel.getAudienceDatas().addAll(audienceDatas);
			chartViewModel.setCategoryModel(categoryModel);
			chartViewModel.setDataType("emission");
			chartViewModel.applyEmission();
		}
		request.setAttribute("Page", "oaudiance_emission");

		return "index";
	}

	@RequestMapping(value = "/oldaudiance/chaine", method = RequestMethod.POST)
	public String oldchainePOST(@RequestParam("date1") String date1, @RequestParam("date2") String date2,
			@RequestParam("chaine") String chaine, HttpServletRequest request) {
		if (!chartViewModel.getDataType().equals("chaine")) {
			CategoryModel categoryModel = new DefaultCategoryModel();
			List<AudienceData> audienceDatas = audienceDataService.getDataByDateIntAndChaine(date1, date2, chaine);
			chartViewModel.getAudienceDatas().clear();
			chartViewModel.getAudienceDatas().addAll(audienceDatas);
			chartViewModel.setCategoryModel(categoryModel);
			chartViewModel.setDataType("chaine");
			chartViewModel.applyChaine();
		}
		request.setAttribute("Page", "oaudiance_chaine");

		return "index";
	}

	@RequestMapping(value = "/oldaudiance/chaine", method = RequestMethod.GET)
	public String oldchaineGet(HttpServletRequest request, HttpSession session) {
		if (!chartViewModel.getDataType().equals("chaine")) {
			CategoryModel categoryModel = new DefaultCategoryModel();
			List<AudienceData> audienceDatas = audienceDataService.getDataByDateIntAndChaine("2017-09-01", "2017-09-08",
					"France 2");
			chartViewModel.getAudienceDatas().clear();
			chartViewModel.getAudienceDatas().addAll(audienceDatas);
			chartViewModel.setCategoryModel(categoryModel);
			chartViewModel.setDataType("chaine");
			chartViewModel.applyChaine();

		}

		request.setAttribute("Page", "oaudiance_chaine");

		return "index";
	}

	@RequestMapping(value = "/oldaudiance/date", method = RequestMethod.GET)
	public String oldDateGet(HttpServletRequest request, HttpSession session) {
		if (!chartViewModel.getDataType().equals("date")) {
			CategoryModel categoryModel = new DefaultCategoryModel();
			List<AudienceData> audienceDatas = audienceDataService.getDataByDate("2017-12-01");
			chartViewModel.getAudienceDatas().clear();
			chartViewModel.getAudienceDatas().addAll(audienceDatas);
			chartViewModel.setCategoryModel(categoryModel);
			chartViewModel.setDataType("date");
			chartViewModel.applyDate();
		}
		request.setAttribute("Page", "oaudiance_date");

		return "index";
	}

	@RequestMapping(value = "/oldaudiance/date", method = RequestMethod.POST)
	public String olddatePOST(@RequestParam("date") String date, HttpServletRequest request) {
		CategoryModel categoryModel = new DefaultCategoryModel();
		List<AudienceData> audienceDatas = audienceDataService.getDataByDate(date);
		chartViewModel.getAudienceDatas().clear();
		chartViewModel.getAudienceDatas().addAll(audienceDatas);
		chartViewModel.setCategoryModel(categoryModel);
		chartViewModel.setDataType("date");
		chartViewModel.applyDate();
		return "redirect:date";
	}

}
