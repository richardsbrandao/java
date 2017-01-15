package br.com.ideais.evaluation.test.service;

import static org.junit.Assert.*;

import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.ideais.evaluation.helpers.Config;
import br.com.ideais.evaluation.model.entity.Candidate;
import br.com.ideais.evaluation.model.service.LinkGeneratorService;

@RunWith(JUnit4.class)
public class LinkGeneratorServiceTest {

	private LinkGeneratorService linkGeneratorService;
	private Candidate candidate;
	private ResourceBundle properties;

	@Before
	public void setUp() {
		linkGeneratorService = new LinkGeneratorService();
		linkGeneratorService.setConfig(new Config());
		candidate = new Candidate();
		candidate.setId("hdaf8ywh8QWEH");
	}
	
	@Test // ver onde por esse test
	public void testIntegration() {
		properties = ResourceBundle.getBundle("ce");
		linkGeneratorService.getConfig().setBaseUrl( properties.getString("ce.baseUrl") );
		String linkExpected = "http://localhost:8080/CandidatesEvaluation/test/introduction/hdaf8ywh8QWEH/";
		assertEquals(linkExpected, linkGeneratorService.getLink(candidate));
	}
	
	@Test
	public void testGetLink() {
		linkGeneratorService.getConfig().setBaseUrl("http://localhost:8080/CandidatesEvaluation/");
		linkGeneratorService.getLink(candidate);
		String linkExpected = "http://localhost:8080/CandidatesEvaluation/test/introduction/hdaf8ywh8QWEH/";
		assertEquals(linkExpected, linkGeneratorService.getLink(candidate));
	}
	
	
}
