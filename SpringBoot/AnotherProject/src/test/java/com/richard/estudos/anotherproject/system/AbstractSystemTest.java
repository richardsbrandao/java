package com.richard.estudos.anotherproject.system;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.richard.estudos.anotherproject.AnotherProjectApplication;

@Profile("test")
@SpringBootTest(classes=AnotherProjectApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:prepare-test.sql")
public class AbstractSystemTest {
	
	@LocalServerPort
	private int port;
	
	protected TestRestTemplate restTemplate = new TestRestTemplate();
	

	protected String endpoint(String uri) {
		return "http://localhost:"+port+uri;
	}
}
