package com.richard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class LoginController {

	@RequestMapping(method=RequestMethod.GET, value="/login")
	public String login(ModelMap modelAndView) {
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	public String home(ModelMap modelAndView) {
		return "home";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/accessDenied")
	public String accessDenied(ModelMap modelAndView) {
		return "accessDenied";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/add")
	public String add(ModelMap modelAndView) {
		return "add";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/permission")
	public String permission(ModelMap modelAndView) {
		return "permission";
	}
	
}
