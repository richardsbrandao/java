package com.richard.estudos.anotherproject.controllers.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.richard.estudos.anotherproject.models.Report;

@Getter @Setter @NoArgsConstructor
public class ReportResponse {

	private Long id;
	private String requester;
	private String externalId;
	private String content;
	
	public ReportResponse(Report report) {
		this.setId(report.getId());
		this.setRequester(report.getRequester());
		this.setExternalId(report.getExternalId().toString());
		this.setContent(report.getContent());
	}

}
