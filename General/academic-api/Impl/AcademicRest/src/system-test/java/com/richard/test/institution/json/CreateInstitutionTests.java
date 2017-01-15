package com.richard.test.institution.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.richard.AbstractSystemTests;
import com.richard.consumer.HttpMethod;
import com.richard.consumer.HttpStatus;
import com.richard.consumer.RestConsumerResponse;
import com.richard.json.Response;
import com.richard.json.RestrictionType;
import com.richard.json.ValidationError;
import com.richard.response.InstitutionResponse;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreateInstitutionTests extends AbstractSystemTests {
	
	@Before
	public void preapreTest() {
		endpoint.addNamespace("institution");
	}
	
	@Test
	public void createInstitution() throws Exception {
		String requestBody = " { \"name\":\"Colégio\" } ";
		
		RestConsumerResponse response = restConsumer.doPost(requestBody);
		InstitutionResponse message = response.fromJsonTo( InstitutionResponse.class );
		
		assertEquals( HttpStatus.CREATED, response.getHttpStatus() );
		assertEquals( HttpMethod.POST, response.getHttpMethod() );
		
		assertNotNull( message.getId() );
		assertNull( message.getName() );
	}
	
	@Test
	public void cantCreateInvalidInstitution() throws Exception {
		String requestBody = " { \"name\":\"\" } ";
		
		RestConsumerResponse response = restConsumer.doPost(requestBody);
		Response message = response.fromJsonTo(Response.class);
		
		assertEquals( HttpStatus.UNPROCESSABLE_ENTITY, response.getHttpStatus() );
		assertEquals( HttpMethod.POST, response.getHttpMethod() );
		
		assertEquals( new Integer(422), message.getHttpStatus() );
		assertEquals( "Erro ao validar a entidade", message.getMessage() );
		
		assertEquals( 1, message.getErrors().size() );
		
		ValidationError error = message.getErrors().get(0);
		assertEquals( "name", error.getField() );
		assertEquals( RestrictionType.REQUIRED, error.getType() );
		assertEquals( "institution.error.name.blank", error.getMessage() );
	}
	
}
