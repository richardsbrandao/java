package com.richard.estudos.anotherproject;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@ActiveProfiles("test")
@SpringBootTest(classes=AnotherProjectApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:prepare-test.sql")
public abstract class AbstractTest {
	
	@LocalServerPort
	private int port;
	
	protected TestRestTemplate restTemplate = new TestRestTemplate();
	

	protected String endpoint(String uri) {
		return "http://localhost:"+port+uri;
	}
	
	protected String endpoint(String uri, ConcurrentHashMap<String, String> queryParam) {
		String endpoint = endpoint(uri);
		String queryParams = queryParam.reduce(1, (k,v) -> (k+"="+v), ((r1, r2) -> r1+"&"+r2));
		return endpoint + "?" + queryParams;
	}
}
