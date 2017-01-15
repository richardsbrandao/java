package br.com.ideais.evaluation.model.dao;

import java.util.List;

import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.helpers.QuestionStatus;
import br.com.ideais.evaluation.model.entity.Question;

public interface QuestionDao {

	public void save(Question question);
	
	public List<Question> listAll();
	
	public List<Question> listAllThatStatusAreNot(QuestionStatus status);

	public Question findById(Question question);

	public List<Question> findByLevel(Level level);

}
