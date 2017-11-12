package com.richard.estudos.anotherproject.system;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.richard.estudos.anotherproject.AbstractTest;
import com.richard.estudos.anotherproject.controllers.requests.ReportRequest;
import com.richard.estudos.anotherproject.controllers.responses.ReportResponse;
import com.richard.estudos.anotherproject.utils.RequestUtils;

@RunWith(SpringRunner.class)
public class CreateAndFindByReportTest extends AbstractTest {

	@SuppressWarnings("serial")
	@Test
	public void create_and_find_report() {
		ReportRequest requestBody = RequestUtils.createReportRequest();
		ResponseEntity<String> response = restTemplate.exchange(endpoint("/categories/report"), HttpMethod.PUT, new HttpEntity<ReportRequest>(requestBody), String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		assertThat(response.getBody()).isNull();
		
		ConcurrentHashMap<String, String> queryParam = new ConcurrentHashMap<String, String>() {{
			put("requester", requestBody.getRequester());
			put("externalId", requestBody.getExternalId().toString());
		}};
		ResponseEntity<ReportResponse> reportResponse = restTemplate.getForEntity(endpoint("/reports/", queryParam), ReportResponse.class);
		
		assertThat(reportResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(reportResponse.getBody().getId()).isNotNull();
		assertThat(reportResponse.getBody().getExternalId()).isEqualTo(requestBody.getExternalId().toString());
		assertThat(reportResponse.getBody().getRequester()).isEqualTo("Richard Brandao");
		assertThat(reportResponse.getBody().getContent()).isEqualTo("[{\"categoryName\":\"DEFENDER\",\"numberOfUsers\":1}]");
	}
}
