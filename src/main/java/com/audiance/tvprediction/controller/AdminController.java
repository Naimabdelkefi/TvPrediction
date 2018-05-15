package com.audiance.tvprediction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.audiance.tvprediction.model.AudienceData;
import com.audiance.tvprediction.model.CronJob;
import com.audiance.tvprediction.service.AudienceDataService;
import com.audiance.tvprediction.service.CronJobService;

@Controller
public class AdminController {

	@Autowired
	AudienceDataService audienceDataService;

	@Autowired
	CronJobService CronJobService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminpage(HttpServletRequest request, ModelMap model) {
		List<AudienceData> audienceDatas = audienceDataService.getAllData();
		model.addAttribute("audienceDatas", audienceDatas);
		request.setAttribute("Page", "admin");
		return "index";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String adminpagePost(@ModelAttribute AudienceData audienceData, HttpServletRequest request, ModelMap model) {
		List<AudienceData> audienceDatas = audienceDataService.getAllData();
		model.addAttribute("audienceDatas", audienceDatas);

		request.setAttribute("Page", "admin");
		return "index";
	}

	@RequestMapping(value = "/admin/cronjob", method = RequestMethod.GET)
	public String cronjobGet(HttpServletRequest request, ModelMap model) {
		List<CronJob> CronJobs = CronJobService.getAllCronJob();
		model.addAttribute("CronJobs", CronJobs);
		request.setAttribute("Page", "cronjob");

		return "index";
	}

	@RequestMapping(value = "/admin/cronjob", method = RequestMethod.POST)
	public String cronjobpost(@ModelAttribute CronJob cronJob, HttpServletRequest request, ModelMap model) {
		CronJobService.saveCronJob(cronJob);
		List<CronJob> CronJobs = CronJobService.getAllCronJob();
		model.addAttribute("CronJobs", CronJobs);
		return "redirect:/admin/cronjob";
	}

	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	public String addData(@ModelAttribute AudienceData audienceData, HttpServletRequest request, ModelMap model) {
		audienceDataService.saveData(audienceData);
		return "redirect:/admin";
	}

	@RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.POST)
	public String addData(@PathVariable(value = "id") long id, HttpServletRequest request, ModelMap model) {
		AudienceData audienceData = audienceDataService.getDataById(id);
		audienceDataService.deleteDataRaw(audienceData);
		return "redirect:/admin";
	}

}
