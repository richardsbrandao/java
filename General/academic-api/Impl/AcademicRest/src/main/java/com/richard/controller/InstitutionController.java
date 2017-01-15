package com.richard.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.richard.domain.Institution;
import com.richard.response.InstitutionResponse;
import com.richard.response.InstitutionListResponse;
import com.richard.service.InstitutionService;

@Controller
@RequestMapping("/institution")
public class InstitutionController extends AbstractController {

	@Autowired
	private InstitutionService institutionService;
	
	@RequestMapping(method = RequestMethod.GET, produces={"application/json", "application/xml"})
	public @ResponseBody Serializable list(HttpServletResponse response) throws Exception {
		List<Institution> institutions = institutionService.findAll();
		return new InstitutionListResponse(institutions);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{institutionId}", produces={"application/json", "application/xml"})
	public @ResponseBody Serializable get(@PathVariable("institutionId") Long institutionId, HttpServletResponse response) throws Exception {
		Institution institution = institutionService.findById(institutionId);
		return new InstitutionResponse(institution);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces={"application/json", "application/xml"})
	@ResponseStatus(value=HttpStatus.CREATED)
	public @ResponseBody Serializable create(@RequestBody InstitutionResponse json, HttpServletResponse response) throws Exception {
		Institution institution = json.toDomain();
		institutionService.create(institution);
		return new InstitutionResponse(institution.getId());
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{institutionId}", produces={"application/json", "application/xml"})
	public @ResponseBody Serializable update(@RequestBody InstitutionResponse json, @PathVariable("institutionId") Long institutionId, HttpServletResponse response) throws Exception {
		Institution institutionRequest = json.toDomain();
		Institution savedInstitution = institutionService.update( institutionId, institutionRequest );
		return new InstitutionResponse( savedInstitution.getId() );
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{institutionId}", produces={"application/json", "application/xml"})
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public @ResponseBody Serializable delete(@PathVariable("institutionId") Long institutionId, HttpServletResponse response) throws Exception {
		institutionService.delete( institutionId );
		return "";
	}

}
