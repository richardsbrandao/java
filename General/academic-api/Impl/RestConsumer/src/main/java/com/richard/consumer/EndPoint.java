package com.richard.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class EndPoint {
	
	private String urlBase;
	private Map<String, String> namespaces = new HashMap<String, String>();

	public EndPoint(String urlBase) {
		this.urlBase = urlBase;
	}
	
	public EndPoint addNamespace(String namespace, String value) {
		this.namespaces.put( namespace, value );
		return this;
	}
	
	public EndPoint addNamespace(String namespace) {
		this.namespaces.put(namespace, null);
		return this;
	}
	
	public String toUri() {
		StringBuilder uri = new StringBuilder(urlBase);
		for( String namespace : namespaces.keySet() ) {
			uri.append(namespace).append("/");
			
			if( StringUtils.isNotBlank( namespaces.get(namespace) ) ) {
				uri.append( namespaces.get(namespace) ).append("/");
			}
		}
		
		return uri.toString();
	}
	
}
