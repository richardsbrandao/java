package br.com.ideais.evaluation.model.service;

import java.util.List;

import br.com.ideais.evaluation.model.dao.CandidateDao;
import br.com.ideais.evaluation.model.entity.Candidate;

public class CandidateService {

	private CandidateDao candidateDao;
	
	public void save(Candidate candidate) {
		candidateDao.save(candidate);
	}

	public List<Candidate> findCandidates() {
		return candidateDao.findAll();
	}

	public Candidate findById(Candidate candidate) {
		return candidateDao.findById(candidate);
	}

	public void setCandidateDao(CandidateDao candidateDao) {
		this.candidateDao = candidateDao;
	}
}
