package com.richard.test.institution.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

@RunWith(SpringJUnit4ClassRunner.class)
public class DeleteInstitutionTests extends AbstractSystemTests {

	@Before
	public void preapreTest() {
		headers.add( new BasicHeader("Accept", "application/xml") );
		restConsumer.setHeaders(headers);
	}
	
	@Test
	public void deleteInstitution() throws Exception {
		endpoint.addNamespace("institution", "1");
		RestConsumerResponse restConsumerResponse = restConsumer.doDelete();

		assertEquals( HttpStatus.NO_CONTENT, restConsumerResponse.getHttpStatus() );
		assertEquals( HttpMethod.DELETE, restConsumerResponse.getHttpMethod() );
		
		assertNull( restConsumerResponse.getResponseText() );
	}
	
	@Test
	public void error404OnDeleteInvalidInstitution() throws Exception {
		endpoint.addNamespace("institution", "999");
		RestConsumerResponse restConsumerResponse = restConsumer.doDelete();
		Response message = restConsumerResponse.fromXmlTo(Response.class);

		assertEquals( HttpStatus.NOT_FOUND, restConsumerResponse.getHttpStatus() );
		assertEquals( HttpMethod.DELETE, restConsumerResponse.getHttpMethod() );
		
		assertEquals( new Integer(404), message.getHttpStatus() );
		assertEquals( "Recurso n�o encontrado", message.getMessage() );
	}

	
}
