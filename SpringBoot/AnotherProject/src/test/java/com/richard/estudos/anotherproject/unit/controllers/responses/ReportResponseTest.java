package com.richard.estudos.anotherproject.unit.controllers.responses;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.richard.estudos.anotherproject.controllers.responses.ReportResponse;
import com.richard.estudos.anotherproject.models.Report;
import com.richard.estudos.anotherproject.utils.ModelUtils;

@RunWith(JUnit4.class)
public class ReportResponseTest {

	private Report report;

	@Before
	public void setUp() {
		this.report = ModelUtils.report();
	}
	
	@Test
	public void to_json() {
		ReportResponse reportResponse = new ReportResponse(report);
		assertThat(reportResponse.getId()).isEqualTo(1L);
		assertThat(reportResponse.getExternalId()).isEqualTo(reportResponse.getExternalId());
		assertThat(reportResponse.getRequester()).isEqualTo("Richard");
		assertThat(reportResponse.getContent()).isEqualTo("[]");
	}
	
}
