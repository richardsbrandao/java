package br.com.ideais.evaluation.model.dao;

import java.util.List;

import br.com.ideais.evaluation.model.entity.Candidate;

public interface CandidateDao {
	public void save(Candidate candidate);
	public List<Candidate> findAll();
	public Candidate findById(Candidate candidate);
	
}
