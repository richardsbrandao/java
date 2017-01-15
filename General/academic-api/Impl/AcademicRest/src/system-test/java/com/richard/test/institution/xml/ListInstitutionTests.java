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
import com.richard.response.InstitutionListResponse;

@RunWith(SpringJUnit4ClassRunner.class)
public class ListInstitutionTests extends AbstractSystemTests {

	@Before
	public void preapreTest() {
		headers.add( new BasicHeader("Accept", "application/xml") );
		restConsumer.setHeaders(headers);
	}
	
	@Test
	public void listAllInstitutionTests() throws Exception {
		endpoint.addNamespace("institution");
		RestConsumerResponse restConsumerResponse = restConsumer.doGet();
		InstitutionListResponse message = restConsumerResponse.fromXmlTo( InstitutionListResponse.class );
		
		assertEquals( HttpStatus.OK, restConsumerResponse.getHttpStatus() );
		assertEquals( HttpMethod.GET, restConsumerResponse.getHttpMethod() );
		
		assert( message.size() > 1 );
	}
	
}
