package com.estudos.ejb.core.template;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

@Stateless
@LocalBean
public class RestTemplate {

	private ResteasyClient client;
	
	public RestTemplate() {
		this.client = new ResteasyClientBuilder().build();
	}
	
	public <T extends Object> void post(String url, T request) {
		Entity<T> entity = Entity.entity(request, MediaType.APPLICATION_JSON);
		
		Response response = client.target(url).request().post(entity);
		
		if( response.getStatus() >= 400 ) {
			throw new RuntimeException("Deu Erro");
		}
		
		response.close();
//		try {
//			HttpPost post = new HttpPost(url);
//			post.addHeader("Accept", "application/json");
//			post.addHeader("Content-Type", "application/json");
//		
//			StringEntity jsonRequest = new StringEntity(request);
//			jsonRequest.setContentType("application/json");
//			post.setEntity(jsonRequest);
//			
//			HttpResponse response = client.execute(post);
//			
//			int statusCode = response.getStatusLine().getStatusCode();
//			
//			if( statusCode == HttpStatus.SC_NO_CONTENT ) {
//				return "";
//			}
//			
//			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//
//			String responseEntity = "";
//			while ((responseEntity = br.readLine()) != null) {
//				responseEntity += responseEntity;
//			}
//			
//			return responseEntity;
//		} catch (Exception e) {
//			return null;
//		}
	}

}
