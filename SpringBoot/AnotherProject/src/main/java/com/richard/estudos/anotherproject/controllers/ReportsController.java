package com.richard.estudos.anotherproject.controllers;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.richard.estudos.anotherproject.controllers.responses.ReportResponse;
import com.richard.estudos.anotherproject.models.Report;
import com.richard.estudos.anotherproject.services.ReportsService;

@RestController
@RequestMapping(value="/reports")
public class ReportsController {

	@Autowired
	private ReportsService reportsService;
	
	@GetMapping(value="/")
	public ResponseEntity<ReportResponse> find(@RequestParam("externalId") UUID externalId, @RequestParam("requester") String requester) {
		Report report = reportsService.findByExternalIdAndRequester(externalId, requester);
		return new ResponseEntity<ReportResponse>(new ReportResponse(report), HttpStatus.OK);
	}
	
}
