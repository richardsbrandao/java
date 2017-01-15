package br.com.ideais.evaluation.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;


@Entity
public class Evaluation {

	@Id
	@SequenceGenerator(name = "evaluation_id", sequenceName = "evaluation_id")
	@GeneratedValue(generator = "evaluation_id", strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(targetEntity=Candidate.class)
	private Candidate candidate;
	private Integer result;
	private Date endDate;
	@Transient
	private List<Question> questions;
	@Transient
	private List<Option> candidateResponses = new ArrayList<Option>();
	
	public void setCandidateResponse(Integer questionIndex, Integer answerIndex) {
		if(getQuantityOfQuestionsInThisEvaluation() > questionIndex) { 
			if(answerIndex != null) {
				candidateResponses.add( questions.get(questionIndex).getOption(answerIndex) );
			} else {
				candidateResponses.add( null );
			}
		}
	}
	public void calculateResult() {
		result = 0;
		for(Option option : candidateResponses) {
			if( wasAnswered(option) ) {
				evaluateCorrectAnswer(option);
			}
		}
	}
	private Boolean wasAnswered(Option option) {
		return option != null;
	}
	private void evaluateCorrectAnswer(Option option) {
		if(option.isCorrect()) {
			result++;
		}
	}
	public Integer getQuantityOfQuestionsInThisEvaluation() {
		return questions.size();
	}
	
	public void setCandidateResponses(List<Option> candidateResponse) {
		this.candidateResponses = candidateResponse;
	}

	public Option getCandidateResponse(Integer responseIndex) {
		return (candidateResponses.size() > responseIndex) ? candidateResponses.get(responseIndex) : null;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> selectQuestions) {
		questions = selectQuestions;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
