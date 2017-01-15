package br.com.ideais.evaluation.controller;

import br.com.ideais.evaluation.helpers.Config;

public class Controller {
	
	protected final String SUCCESS = "success";
	protected final String FAIL = "fail";
	
	private Config config;

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	
	
}
