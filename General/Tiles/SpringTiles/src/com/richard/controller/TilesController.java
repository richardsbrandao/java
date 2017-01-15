package com.richard.controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TilesController extends ActionSupport {

	public String defaultTemplate() {
		return SUCCESS;
	}
	
	public String defaultTemplatePage2() {
		return SUCCESS;
	}
	
//	public String defaultTemplateWithIncrement() {
//		return SUCCESS;
//	}
//	
//	public String usingDifferentsResources() {
//		return SUCCESS;
//	}
	
}
