package br.com.ideais.evaluation.model.service;

import java.util.List;

import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.helpers.QuestionStatus;
import br.com.ideais.evaluation.model.dao.QuestionDao;
import br.com.ideais.evaluation.model.entity.Option;
import br.com.ideais.evaluation.model.entity.Question;

public class QuestionService {
	
	private QuestionDao questionDao;
	
	public void save(Question question){
		relateOptionWith(question);
		questionDao.save(question);
	}
	
	private void relateOptionWith(Question question) {
		if(question.hasOptions()) {
			for(Option option : question.getOptions()) {
				option.setQuestion(question);
			}
		}
	}

	public void update(Question question) {
		relateOptionWith(question);
		questionDao.save(question);
	}

	public void delete(Question question) {
		questionDao.save(question);
	}
	
	public List<Question> listAllThatAreNotDeleted() {
		return questionDao.listAllThatStatusAreNot(QuestionStatus.DELETED);
	}
	
	public Question findQuestionById(Question question) {
		return questionDao.findById(question);
	}
	
	public List<Question> listAll() {
		return questionDao.listAll();
	}
	
	public QuestionDao getQuestionDao() {
		return questionDao;
	}
	
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public List<Question> findByLevel(Level level) {
		return questionDao.findByLevel(level);
	}
}
