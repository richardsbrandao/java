package com.richard.ehcache.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.richard.ehcache.domain.Event;
import com.richard.ehcache.service.EhCacheService;

@Controller
public class AssController {

	@Autowired
	private EhCacheService ehCacheService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody List<Event> testandoEhCache(HttpServletRequest request, HttpServletResponse response) {
		return ehCacheService.findByCategory(2);
	}
	
}
