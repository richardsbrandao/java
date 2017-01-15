package com.richard.consumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

public class RestConsumer {

	private HttpClient client;
	private EndPoint endpoint;
	private HttpRequestBase request;
	private HttpResponse response;
	private List<Header> headers;
	
	public RestConsumer() {
		client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, getTimeoutInMinutes( 5 ));
		headers = new ArrayList<Header>();
	}

	public RestConsumerResponse doPost(String requestBody) throws Exception {
		request = MethodFactory.instance( HttpMethod.POST, endpoint.toUri(), requestBody );
		return doRequest();
	}
	
	public RestConsumerResponse doPut(String requestBody) throws Exception {
		request = MethodFactory.instance( HttpMethod.PUT, endpoint.toUri(), requestBody );
		return doRequest();
	}
	
	public RestConsumerResponse doGet() throws Exception {
		request = MethodFactory.instance( HttpMethod.GET , endpoint.toUri() );
		return doRequest();
	}
	
	public RestConsumerResponse doDelete() throws Exception {
		request = MethodFactory.instance( HttpMethod.DELETE , endpoint.toUri() );
		return doRequest();
	}

	private RestConsumerResponse doRequest() throws Exception {
		for( Header header : headers ) {
			request.addHeader(header);
		}
		response = client.execute( request );
		return getResponse();
	}

	private RestConsumerResponse getResponse() throws Exception {
		Integer responseCode = getResponseCode();
		RestConsumerResponse restResponse = new RestConsumerResponse( HttpStatus.findByCode(responseCode), HttpMethod.findByMethod( getHttpRequestVerb() ) );
		
		if( restResponse.is204() ) {
			return restResponse;
		}

		HttpEntity responseEntity = response.getEntity();
		StringBuffer responseText = new StringBuffer();
		
		try {
			BufferedReader reader = new BufferedReader( new InputStreamReader( responseEntity.getContent() ) );
			
			String responseLine;
			while( (responseLine = reader.readLine()) != null ) {
				responseText.append( responseLine );
			}
			
			restResponse.setResponseText( responseText.toString() );
		} finally {
			closeConnection();
		}
		
		return restResponse;
	}

	private void closeConnection() throws Exception {
		response.getEntity().getContent().close();
	}

	private String getHttpRequestVerb() {
		return request.getClass().getSimpleName().toLowerCase().replace("http", "");
	}

	private Integer getResponseCode() {
		return response.getStatusLine().getStatusCode();
	}

	private int getTimeoutInMinutes(Integer min) {
		return min * 60 * 1000;
	}

	public void setEndpoint(EndPoint endpoint) {
		this.endpoint = endpoint;
	}

	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}

}
