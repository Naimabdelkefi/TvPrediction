package com.audiance.tvprediction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.audiance.tvprediction.model.AudienceData;
import com.audiance.tvprediction.service.AudienceDataService;

@Controller
public class AdminController {

	@Autowired
	AudienceDataService audienceDataService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminpage(HttpServletRequest request, ModelMap model) {
		List<AudienceData> audienceDatas = audienceDataService.getAllData();
		model.addAttribute("audienceDatas", audienceDatas);
		request.setAttribute("Page", "admin");
		return "index";
	}
	@RequestMapping(value = "/admin/cronjob", method = RequestMethod.GET)
	public String cronjobGet(HttpServletRequest request) {
		request.setAttribute("Page", "cronjob");

		return "index";
	}

}
