package com.richard.test.institution.json;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.richard.AbstractSystemTests;
import com.richard.consumer.HttpMethod;
import com.richard.consumer.HttpStatus;
import com.richard.consumer.RestConsumerResponse;
import com.richard.response.InstitutionListResponse;

@RunWith(SpringJUnit4ClassRunner.class)
public class ListInstitutionTests extends AbstractSystemTests {

	@Test
	public void listAllInstitutionTests() throws Exception {
		endpoint.addNamespace("institution");
		RestConsumerResponse restConsumerResponse = restConsumer.doGet();
		InstitutionListResponse message = restConsumerResponse.fromJsonTo( InstitutionListResponse.class );
		
		assertEquals( HttpStatus.OK, restConsumerResponse.getHttpStatus() );
		assertEquals( HttpMethod.GET, restConsumerResponse.getHttpMethod() );
		
		assert( message.size() > 1 );
	}
	
}
