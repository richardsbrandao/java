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
import br.com.ideais.evaluation.helpers.QuestionStatus;
import br.com.ideais.evaluation.model.dao.QuestionDao;
import br.com.ideais.evaluation.model.entity.Option;
import br.com.ideais.evaluation.model.entity.Question;
import br.com.ideais.evaluation.model.service.QuestionService;

@RunWith(JUnit4.class)
public class QuestionServiceTest {
	
	private Question question;
	private List<Option> options;
	private QuestionService questionService;
	private QuestionDao questionDao;

	@Before
	public void setUp() {
		questionDao = createMock(QuestionDao.class);
		questionService = new QuestionService();
		questionService.setQuestionDao(questionDao);
		question = runQuestion("Descobrimento", "Quem descobriu o brasil?", QuestionStatus.ACTIVE, Level.JUNIOR);
		options = new ArrayList<Option>();
		options.add(runOption("Pedro"));
		options.add(runOption("Pero"));
		options.add(runOption("Vasco"));
		options.add(runOption("Cristovão"));
		options.add(runOption("O Chapolím colorado"));
		question.setOptions(options);
	}
	
	@Test
	public void testSaveQuestion() {
		questionDao.save(question);
		expect(questionDao).times(1);
		replay(questionDao);
		
		questionService.save(question);
		verify(questionDao);
		assertNotNull(question.getOption(0).getQuestion());
		assertNotNull(question.getOption(1).getQuestion());
		assertNotNull(question.getOption(2).getQuestion());
		assertNotNull(question.getOption(3).getQuestion());
		assertNotNull(question.getOption(4).getQuestion());
	}
	
	@Test
	public void testUpdateQuestion() {
		questionDao.save(question);
		expect(questionDao).times(1);
		replay(questionDao);
		
		questionService.update(question);
		verify(questionDao);
		assertNotNull(question.getOption(0).getQuestion());
		assertNotNull(question.getOption(1).getQuestion());
		assertNotNull(question.getOption(2).getQuestion());
		assertNotNull(question.getOption(3).getQuestion());
		assertNotNull(question.getOption(4).getQuestion());
	}
	
	@Test
	public void testDeleteQuestion() {
		questionDao.save(question);
		expect(questionDao).times(1);
		replay(questionDao);
		
		questionService.delete(question);
		verify(questionDao);
	}

	private Question runQuestion(String title, String description, QuestionStatus status, Level level) {
		Question question = new Question();
		question.setTitle(title);
		question.setDescription(description);
		question.setStatus(status);
		question.setLevel(level);
		return question;
	}

	private Option runOption(String answer) {
		Option option = new Option();
		option.setAnswer(answer);
		return option;
	}
	
}
