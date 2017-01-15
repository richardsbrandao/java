package br.com.ideais.evaluation.model.service;

import br.com.ideais.evaluation.helpers.Config;
import br.com.ideais.evaluation.model.entity.Candidate;

public class LinkGeneratorService {

	private Config config;
	
	public String getLink(Candidate candidate) {
		return getUrl() + candidate.getId() + "/";
	}
	private String getUrl() {
		return config.getBaseUrl()+"test/introduction/";
	}
	public Config getConfig() {
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
	}
	
	
}
