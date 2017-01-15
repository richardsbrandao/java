package com.richard.test.institution.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
public class UpdateInstitutionTests extends AbstractSystemTests {

	@Test
	public void updateInstitution() throws Exception {
		endpoint.addNamespace("institution", "1");
		String requestBody = " { \"name\":\"Colégio\" } ";
		
		RestConsumerResponse restConsumerResponse = restConsumer.doPut(requestBody);
		InstitutionResponse message = restConsumerResponse.fromJsonTo( InstitutionResponse.class );
		
		assertEquals( new Long(1), message.getId() );
		assertNull( message.getName() );
	}
	
	@Test
	public void error422OnUpdateInstitution() throws Exception {
		endpoint.addNamespace("institution", "1");
		String requestBody = " { \"name\":\"\" } ";
		
		RestConsumerResponse restConsumerResponse = restConsumer.doPut(requestBody);
		Response message = restConsumerResponse.fromJsonTo(Response.class);
		
		assertEquals( new Integer(422), message.getHttpStatus() );
		assertEquals( "Erro ao validar a entidade", message.getMessage() );

		assertEquals( 1, message.getErrors().size() );
		
		ValidationError error = message.getErrors().get(0);
		assertEquals( "name", error.getField() );
		assertEquals( RestrictionType.REQUIRED, error.getType() );
		assertEquals( "institution.error.name.blank", error.getMessage() );
	}
	
	@Test
	public void error404OnUpdateInstitution() throws Exception {
		endpoint.addNamespace("institution", "9999");
		String requestBody = " { \"name\":\"Colégio\" } ";
		
		RestConsumerResponse restConsumerResponse = restConsumer.doPut(requestBody);
		Response message = restConsumerResponse.fromJsonTo( Response.class );

		assertEquals( HttpStatus.NOT_FOUND, restConsumerResponse.getHttpStatus() );
		assertEquals( HttpMethod.PUT, restConsumerResponse.getHttpMethod() );
		
		assertEquals( new Integer(404), message.getHttpStatus() );
		assertEquals( "Recurso não encontrado", message.getMessage() );
	}
	
}
