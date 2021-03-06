package com.richard.test.institution.xml;

import static org.junit.Assert.assertEquals;

import org.apache.http.message.BasicHeader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.richard.AbstractSystemTests;
import com.richard.consumer.HttpMethod;
import com.richard.consumer.HttpStatus;
import com.richard.consumer.RestConsumerResponse;
import com.richard.json.Response;
import com.richard.response.InstitutionResponse;

@RunWith(SpringJUnit4ClassRunner.class)
public class GetInstitutionTests extends AbstractSystemTests {

	@Before
	public void preapreTest() {
		headers.add( new BasicHeader("Accept", "application/xml") );
		restConsumer.setHeaders(headers);
	}
	
	@Test
	public void getInstitution() throws Exception {
		endpoint.addNamespace("institution", "1");
		RestConsumerResponse response = restConsumer.doGet();
		InstitutionResponse message = response.fromXmlTo(InstitutionResponse.class);

		assertEquals( HttpStatus.OK, response.getHttpStatus() );
		assertEquals( HttpMethod.GET, response.getHttpMethod() );
		
		assertEquals( 1L, message.getId(), 1 );
		assertEquals( "Simonsen", message.getName() );
	}
	
	@Test
	public void getInexistentInstitution() throws Exception {
		endpoint.addNamespace("institution", "99");
		RestConsumerResponse response = restConsumer.doGet();
		Response message = response.fromXmlTo(Response.class);

		assertEquals( HttpStatus.NOT_FOUND, response.getHttpStatus() );
		assertEquals( HttpMethod.GET, response.getHttpMethod() );
		
		assertEquals( new Integer(404), message.getHttpStatus() );
		assertEquals( "Recurso n�o encontrado", message.getMessage() );
	}
	
}
