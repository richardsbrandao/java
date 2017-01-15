package com.richard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.richard.consumer.EndPoint;
import com.richard.consumer.RestConsumer;
import com.richard.test.AbstractTests;

@Ignore
@ContextConfiguration({
	"classpath:spring/applicationContext.xml",
	"classpath:spring/dataContext.xml",
	"classpath:spring/systemTestContext.xml"
})
public class AbstractSystemTests extends AbstractTests {

	private String urlBase = System.getProperty("urlBase", "http://localhost:8080/AcademicRest/");
	
	protected EndPoint endpoint = new EndPoint(urlBase);
	
	@Autowired
	protected RestConsumer restConsumer;
	
	protected Map<String, String> parameters = new HashMap<String, String>();
	protected List<Header> headers = new ArrayList<Header>();
	
	@Before
	public void prepareTest() {
		restConsumer.setEndpoint( endpoint );
		headers = new ArrayList<Header>();
		restConsumer.setHeaders(headers);
	}
	
	@Override
	public String getPrepareScriptSqlFileName() {
		return "database/prepareSystemTestsDatabase.sql";
	}

}
