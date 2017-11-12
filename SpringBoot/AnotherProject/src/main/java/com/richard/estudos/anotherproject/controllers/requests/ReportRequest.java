package com.richard.estudos.anotherproject.controllers.requests;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReportRequest {

	private String requester;
	private UUID externalId;
	
}
