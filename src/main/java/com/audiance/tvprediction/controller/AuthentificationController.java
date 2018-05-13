package com.audiance.tvprediction.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audiance.tvprediction.model.User;
import com.audiance.tvprediction.service.UserService;

@Controller
public class AuthentificationController {
	@Autowired
	UserService userService;
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(HttpServletRequest request) {
		request.setAttribute("Page", "login");

		return "index";
	}



	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(@ModelAttribute User user, HttpServletRequest request) {
		if (userService.LoggedIn(user)) {
			return "redirect:home";
		} else {
			return "redirect:login";
		}
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String SignupPage(HttpServletRequest request) {

		request.setAttribute("Page", "signup");

		return "index";
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	@ResponseBody
	public String home() {
		return "Home";

	}

}
