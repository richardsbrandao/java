package br.com.ideais.evaluation.controller;

import java.util.List;

import br.com.ideais.evaluation.helpers.QuestionStatus;
import br.com.ideais.evaluation.model.entity.Question;
import br.com.ideais.evaluation.model.service.QuestionService;

public class QuestionController extends Controller {
	private QuestionService questionService;
	private Question question;
	private Integer correctAnswer;
	private List<Question> questions;
	
	public String save() {
		question.setStatus(QuestionStatus.ACTIVE);
		question.setCorrectOption(correctAnswer);
		questionService.save(question);
		return SUCCESS;
	}

	public String loadEditQuestion() {
		question = questionService.findQuestionById(question);
		return SUCCESS;
	}
	public String update() {
		question.setCorrectOption(correctAnswer);
		questionService.update(question);
		return SUCCESS;
	}
	public String delete() {
		loadEditQuestion();
		question.setStatus(QuestionStatus.DELETED);
		questionService.delete(question);
		return SUCCESS;
	}
	public String load() {
		questions = questionService.listAllThatAreNotDeleted();
		return SUCCESS;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public QuestionService getQuestionService() {
		return questionService;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	public Integer getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(Integer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}
