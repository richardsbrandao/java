package br.com.ideais.evaluation.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.ideais.evaluation.helpers.Filter;
import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.model.dao.EvaluationDao;
import br.com.ideais.evaluation.model.dao.QuestionDao;
import br.com.ideais.evaluation.model.entity.Candidate;
import br.com.ideais.evaluation.model.entity.Evaluation;
import br.com.ideais.evaluation.model.entity.Question;

public class EvaluationService {
	
	private QuestionDao questionDao;
	private Integer numberOfQuestions;
	private EvaluationDao evaluationDao;
	private Integer minutesToEndDate;
	private Integer hoursToEndDate;
	
	public Evaluation generateEvaluation(Candidate candidate) {
		Evaluation evaluation = new Evaluation();
		evaluation.setCandidate(candidate);
		evaluation.setQuestions( selectQuestions(candidate.getLevel()) );
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minutesToEndDate);
		calendar.add(Calendar.HOUR, hoursToEndDate);
		evaluation.setEndDate(calendar.getTime());
		return evaluation;
	}
	
	private List<Question> selectQuestions(Level level) {
		List<Question> allQuestions = questionDao.findByLevel(level);
		List<Question> selectQuestions = new ArrayList<Question>();
		Integer indexQuestion;
		while(selectQuestions.size() < getMaxNumberOfQuestionsPossible(allQuestions)) {
			indexQuestion = (int) (Math.random() * allQuestions.size());
			if( !selectQuestions.contains(allQuestions.get(indexQuestion)) ) {
				selectQuestions.add(allQuestions.get(indexQuestion));
			}
		}
		return selectQuestions;
	}
	
	public List<Evaluation> generateReport(Filter filter) {
		return evaluationDao.findByDatesAndLevel(filter.getBeginning(), filter.getEnd(), filter.getLevel());
	}

	private int getMaxNumberOfQuestionsPossible(List<Question> allQuestions) {
		return (allQuestions.size() < numberOfQuestions) ? allQuestions.size() : numberOfQuestions;
	}
	
	public void save(Evaluation evaluation) {
		evaluationDao.save(evaluation);
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public void setNumberOfQuestions(Integer numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public void setEvaluationDao(EvaluationDao evaluationDao) {
		this.evaluationDao = evaluationDao;
	}

	public Integer getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public Integer getMinutesToEndDate() {
		return minutesToEndDate;
	}

	public void setMinutesToEndDate(Integer minutesToEndDate) {
		this.minutesToEndDate = minutesToEndDate;
	}

	public void setHoursToEndDate(Integer hoursToEndDate) {
		this.hoursToEndDate = hoursToEndDate;
	}



}
