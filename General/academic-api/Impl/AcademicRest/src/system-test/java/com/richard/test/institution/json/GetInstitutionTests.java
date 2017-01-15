package com.richard.test.institution.json;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.richard.AbstractSystemTests;
import com.richard.consumer.HttpMethod;
import com.richard.consumer.HttpStatus;
import com.richard.consumer.RestConsumerResponse;
import com.richard.domain.Institution;
import com.richard.json.Response;

@RunWith(SpringJUnit4ClassRunner.class)
public class GetInstitutionTests extends AbstractSystemTests {

	@Test
	public void getInstitution() throws Exception {
		endpoint.addNamespace("institution", "1");
		RestConsumerResponse response = restConsumer.doGet();
		Institution message = response.fromJsonTo(Institution.class);

		assertEquals( HttpStatus.OK, response.getHttpStatus() );
		assertEquals( HttpMethod.GET, response.getHttpMethod() );
		
		assertEquals( 1L, message.getId(), 1 );
		assertEquals( "Simonsen", message.getName() );
	}
	
	@Test
	public void getInexistentInstitution() throws Exception {
		endpoint.addNamespace("institution", "99");
		RestConsumerResponse response = restConsumer.doGet();
		Response message = response.fromJsonTo(Response.class);

		assertEquals( HttpStatus.NOT_FOUND, response.getHttpStatus() );
		assertEquals( HttpMethod.GET, response.getHttpMethod() );
		
		assertEquals( new Integer(404), message.getHttpStatus() );
		assertEquals( "Recurso não encontrado", message.getMessage() );
	}
	
}
