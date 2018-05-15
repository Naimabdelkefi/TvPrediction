package com.audiance.tvprediction.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;

import com.audiance.tvprediction.model.AudienceData;
import com.audiance.tvprediction.service.AudienceDataService;
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

	@RequestMapping(value = { "/oldaudiance/emission", "/oldaudiance/emission1" }, method = RequestMethod.GET)

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
		
		return "redirect:chaine";
		
	}

	@RequestMapping(value = "/oldaudiance/chaine", method = RequestMethod.GET)
	public String oldchaineGet(HttpServletRequest request, HttpSession session) {
		if (!chartViewModel.getDataType().equals("chaine")) {
			CategoryModel categoryModel = new DefaultCategoryModel();
			List<AudienceData> audienceDatas = audienceDataService.getDataByDateIntAndChaine("2017-10-01", "2017-10-08",
					"France 3");
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

	@RequestMapping(value = "/prediction", method = RequestMethod.GET)
	public String predict(HttpServletRequest request, ModelMap model) throws IOException {
		request.setAttribute("Page", "pred");
		model.addAttribute("str", "-1");
		return "index";
	}

	@RequestMapping(value = "/prediction", method = RequestMethod.POST)
	public String predictionPost(@RequestParam("date") String date, @RequestParam("chaine") String chaine,
			@RequestParam("emission") String emission, @RequestParam("heure") String heure, HttpServletRequest request,
			ModelMap model) throws IOException {
		Runtime r = Runtime.getRuntime();
		String contextPath = request.getRealPath(File.separator);
		String dir = contextPath + File.separator + "scripts" + File.separator;
		Process p = r.exec("cmd /c " + dir + "a.bat " + dir + " \"" + date + "\" \"" + emission + "\" \"" + chaine
				+ "\" \"" + heure + "\"");
		BufferedReader is = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = is.readLine();
        p.destroy();
		request.setAttribute("Page", "pred");
		model.addAttribute("str", line);
		System.out.println(line);
		return "index";
	}

}
