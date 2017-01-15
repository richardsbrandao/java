package br.com.ideais.evaluation.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ideais.evaluation.model.dao.AbstractDao;
import br.com.ideais.evaluation.model.dao.CandidateDao;
import br.com.ideais.evaluation.model.entity.Candidate;

@Transactional(propagation=Propagation.REQUIRED)
public class CandidateDaoImpl extends AbstractDao<Candidate> implements CandidateDao {

	protected CandidateDaoImpl() {
		super(Candidate.class);
	}

	@Override
	public void save(Candidate candidate) {
		saveEntity(candidate);
	}

	@Override
	public List<Candidate> findAll() {
		return findAll();
	}

	public Candidate findById(Candidate candidate) {
		Criteria criteria = session().createCriteria(Candidate.class);
		criteria.add(Restrictions.eq("id", candidate.getId()));
		return (Candidate) criteria.list().get(0);
	}

}
