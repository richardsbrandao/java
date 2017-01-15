package br.com.ideais.evaluation.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.helpers.QuestionStatus;
import br.com.ideais.evaluation.model.dao.AbstractDao;
import br.com.ideais.evaluation.model.dao.QuestionDao;
import br.com.ideais.evaluation.model.entity.Question;

@Transactional(propagation=Propagation.REQUIRED)
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

	protected QuestionDaoImpl() {
		super(Question.class);
	}

	@Override
	public void save(Question question) {
		saveEntity(question);
	}
	
	@Override
	public List<Question> listAll() {
		return super.listAll();
	}

	@Override
	public Question findById(Question question) {
		return super.findById(question.getId());
	}
	
	@SuppressWarnings("unchecked")
	@Override 
	public List<Question> findByLevel(Level level) {
		Criteria criteria = session().createCriteria(Question.class);
		criteria.add(Restrictions.eq("status", QuestionStatus.ACTIVE));
		criteria.add(Restrictions.eq("level", level));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> listAllThatStatusAreNot(QuestionStatus status) {
		return session().createQuery("from Question question where question.status != :status")
						.setParameter("status", status).list();
	}
	
}
