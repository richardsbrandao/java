package com.richard.estudos.anotherproject.models;

import lombok.Getter;

@Getter
public class ReportCategoriesStatistics {

	private String categoryName;
	private Long numberOfUsers;
	
	public ReportCategoriesStatistics(String categoryName, Long numberOfUsers) {
		this.categoryName = categoryName;
		this.numberOfUsers = numberOfUsers;
	}
}
