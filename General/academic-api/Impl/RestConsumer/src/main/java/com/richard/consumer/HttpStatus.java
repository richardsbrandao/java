package com.richard.consumer;

public enum HttpStatus {

	  OK(200),
	  CREATED(201),
	  NO_CONTENT(204),
	  MOVED(301),
	  MOVED_TEMPORARILY(302),
	  NOT_MODIFIED(304),
	  BAD_REQUEST(400),
	  UNAUTHORIZED(401),
	  FORBIDDEN(403),
	  NOT_FOUND(404),
	  METHOD_NOT_ALLOWED(405),
	  NOT_ACCEPTABLE(406),
	  REQUEST_TIMEOUT(408),
	  CONFLICT(409),
	  GONE(410),
	  UNPROCESSABLE_ENTITY(422),
	  INTERNAL_SERVER_ERROR(500);
	  
	  private Integer code;
	  
	  HttpStatus(Integer code) {
		  this.code = code;
	  }

	  public Integer getCode() {
		  return code;
	  }
	  
	  public static HttpStatus findByCode(Integer code) {
		    for( HttpStatus status : values() ) {
			      if( status.code.equals( code ) ) {
			    	  return status;
			      }
		    }
		    return null;
	  }
	
}
