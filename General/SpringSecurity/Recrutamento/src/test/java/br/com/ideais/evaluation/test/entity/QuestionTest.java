package br.com.ideais.evaluation.test.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.helpers.QuestionStatus;
import br.com.ideais.evaluation.model.entity.Option;
import br.com.ideais.evaluation.model.entity.Question;

@RunWith(JUnit4.class)
public class QuestionTest {
	
	private Question question;
	private List<Option> options;
	
	@Before
	public void setUp() {
		question = runQuestion("Descobrimento", "Quem descobriu o brasil?", QuestionStatus.ACTIVE, Level.JUNIOR);
		options = new ArrayList<Option>();
		options.add(runOption("Pedro"));
		options.add(runOption("Pero"));
		options.add(runOption("Vasco"));
		options.add(runOption("Cristovão"));
		options.add(runOption("O Chapolím colorado"));
		question.setOptions(options);
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

	@Test
	public void testSetCorrectOption() {
		question.setCorrectOption(0);
		assertTrue(question.getOptions().get(0).isCorrect());
	}
	
	@Test
	public void testGetValidOption() {
		assertNotNull(question.getOption(3));
	}
	
	@Test
	public void testGetInvalidOption() {
		assertNull(question.getOption(5));
	}
	
	@Test
	public void testInvalidCorrectOption() {
		question.setCorrectOption(5);
		assertNull(question.getCorrectOption());
	}
	
	@Test
	public void testNoCorrectOption() {
		assertNull(question.getCorrectOption());	
	}
	
	@Test
	public void testCorrectOption() {
		question.setCorrectOption(3);
		Option correctOption = question.getCorrectOption();
		assertNotNull(correctOption);
		assertEquals("Cristovão", correctOption.getAnswer());
	}
	
	@Test
	public void testHasOption() {
		assertTrue(question.hasOptions());
	}
	
	@Test
	public void testHasNoOption() {
		Question questionNoOption = new Question();
		assertFalse(questionNoOption.hasOptions());
		questionNoOption.setOptions(new ArrayList<Option>());
		assertFalse(questionNoOption.hasOptions());
	}
}
