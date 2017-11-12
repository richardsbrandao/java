package com.richard.estudos.anotherproject.utils;

import java.util.UUID;

import com.richard.estudos.anotherproject.controllers.requests.CategoryRequest;
import com.richard.estudos.anotherproject.controllers.requests.ReportRequest;
import com.richard.estudos.anotherproject.controllers.requests.UserRequest;

public class RequestUtils {

	public static UserRequest createUserRequest() {
		UserRequest userRequest = new UserRequest();
		userRequest.setName("Richard Brandao");
		userRequest.setBornDate("1991-11-26");
		userRequest.setCategoryId(1L);
		return userRequest;
	}

	public static CategoryRequest createCategoryRequest() {
		CategoryRequest categoryRequest = new CategoryRequest();
		categoryRequest.setName("GOALKEEPER");
		return categoryRequest;
	}

	public static ReportRequest createReportRequest() {
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setExternalId(UUID.randomUUID());
		reportRequest.setRequester("Richard Brandao");
		return reportRequest;
	}
	
}
