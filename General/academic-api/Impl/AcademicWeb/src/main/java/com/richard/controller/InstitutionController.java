package com.richard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.richard.exception.ValidateException;
import com.richard.request.InstitutionRequest;
import com.richard.service.InstitutionService;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

	@Autowired
	private InstitutionService institutionService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method=RequestMethod.GET, value="/create")
	public String create(ModelMap map) {
		return "createInstitution";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/save")
    public ModelAndView processForm(InstitutionRequest institutionRequest, BindingResult result, ModelAndView model) {
		try {
			institutionService.save( institutionRequest );
			model.setViewName("redirect:/institution/list");
			model.addObject("message", messageSource.getMessage("institution.create.success", null, "institution.create.success", null) );
			model.addObject("messageKey", "success");
		} catch( ValidateException e ) {
			model.setViewName("createInstitution");
			model.addObject("message", e.getMessage());
			model.addObject("messageKey", "error");
		} catch( Exception e ) {
			model.setViewName("createInstitution");
			model.addObject("message", e.getMessage());
			model.addObject("messageKey", "error");
		}
		return model;
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/list")
	public ModelAndView list(ModelAndView model, HttpServletRequest request) {
		model.setViewName("listInstitution");
		model.addObject("institutions", institutionService.findAll());
		model.addObject("messageKey", request.getParameter("messageKey"));
		model.addObject("message", request.getParameter("message"));
		return model;
	}
	
}
