package com.richard.studies.test;

import java.util.ResourceBundle;

public class SqlResourceBundle {
	
	private ResourceBundle resource;
	
	public SqlResourceBundle() {
		resource = ResourceBundle.getBundle("database/sql");
	}
	
	public String getSql( String key ) {
		return resource.getString(key);
	}

}
