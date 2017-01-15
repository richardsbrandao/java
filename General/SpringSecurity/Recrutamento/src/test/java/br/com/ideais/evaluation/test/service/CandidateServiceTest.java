package br.com.ideais.evaluation.test.service;


import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.model.dao.CandidateDao;
import br.com.ideais.evaluation.model.entity.Candidate;
import br.com.ideais.evaluation.model.service.CandidateService;

@RunWith(JUnit4.class)
public class CandidateServiceTest {
	
	private CandidateService candidateService;
	private CandidateDao candidateDao;
	private Candidate candidate;
	
	@Before
	public void setUp() {
		candidateService = new CandidateService();
		candidateDao = createMock(CandidateDao.class);
		candidateService.setCandidateDao(candidateDao);
		runCandidate();
	}
	
	@Test
	public void testSaveCandidate() {
		candidateDao.save(candidate);
		expect(candidateDao).times(1);
		replay(candidateDao);
		
		candidateService.save(candidate);
		verify(candidateDao);
	}

	private void runCandidate() {
		candidate = new Candidate();
		candidate.setEmail("richard.brandao@ideais.com.br");
		candidate.setLevel(Level.JUNIOR);
		candidate.setName("Richard");
	}

	@Test
	public void testFindCandidates() {
		expect(candidateDao.findAll()).andReturn(new ArrayList<Candidate>()).times(1);
		replay(candidateDao);
		
		List<Candidate> candidates = candidateService.findCandidates();
		verify(candidateDao);
		assertEquals(new ArrayList<Candidate>(), candidates);
	}

	@Test
	public void testFindById() {
		Candidate candidateSearch = new Candidate();
		candidateSearch.setId("fd457f78b");
		candidate.setId("fd457f78b");
		expect(candidateDao.findById(candidateSearch)).andReturn(candidate).times(1);
		replay(candidateDao);
		
		Candidate candidateReturn = candidateService.findById(candidateSearch);
		verify(candidateDao);
		assertEquals(candidate, candidateReturn);
	}
	
	
}
