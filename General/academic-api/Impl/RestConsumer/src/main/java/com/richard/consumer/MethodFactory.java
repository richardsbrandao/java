package com.richard.consumer;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

public class MethodFactory {

	public static HttpRequestBase instance(HttpMethod method, String endpoint, String requestBody) {
		switch( method ) {
			case POST:
				return preparePost(endpoint, requestBody);
			case GET:
				return prepareGet(endpoint);
			case PUT:
				return preparePut(endpoint, requestBody);
			case DELETE:
				return prepareDelete(endpoint);
			default:
				return null;
		}
	}
	
	public static HttpRequestBase instance(HttpMethod method, String endpoint) {
		return instance(method, endpoint, null);
	}


	private static HttpRequestBase prepareDelete(String endpoint) {
		return new HttpDelete( endpoint );
	}

	private static HttpRequestBase preparePut(String endpoint, String requestBody) {
		HttpPut put = new HttpPut( endpoint );
		put.setEntity( new StringEntity(requestBody, ContentType.create("application/json", "UTF-8")) );
		return put;
	}

	private static HttpRequestBase prepareGet(String endpoint) {
		return new HttpGet( endpoint );
	}

	private static HttpRequestBase preparePost(String endpoint, String requestBody) {
		HttpPost post = new HttpPost( endpoint );
		post.setEntity( new StringEntity(requestBody, ContentType.create("application/json", "UTF-8")) );
		return post;
	}

}
