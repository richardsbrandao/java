package br.com.ideais.evaluation.test.entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.helpers.QuestionStatus;
import br.com.ideais.evaluation.model.entity.Evaluation;
import br.com.ideais.evaluation.model.entity.Option;
import br.com.ideais.evaluation.model.entity.Question;

@RunWith(JUnit4.class)
public class EvaluationTest {

	private Evaluation evaluation;
	private List<Question> questions;
	private List<Option> options;
	
	@Before
	public void setUp() {
		evaluation = new Evaluation();
		questions = new ArrayList<Question>();
		runFirstQuestion();
		runSecondQuestion();
		runThirdQuestion();
		evaluation.setQuestions(questions);
	}
	
	@Test
	public void testSetCandidateReponse() {
		evaluation.setCandidateResponse(0, 0);
		evaluation.setCandidateResponse(1, 1);
		evaluation.setCandidateResponse(2, 2);

		assertEquals(evaluation.getQuestions().get(0).getOption(0), evaluation.getCandidateResponse(0)); 
		assertEquals(evaluation.getQuestions().get(1).getOption(1), evaluation.getCandidateResponse(1));
		assertEquals(evaluation.getQuestions().get(2).getOption(2), evaluation.getCandidateResponse(2));
	}
	
	@Test
	public void testSetUnknowQuestion() {
		evaluation.setCandidateResponse(18, 3);
		assertNull(evaluation.getCandidateResponse(18));
	}
	
	@Test
	public void testGetValidCandidateResponse() {
		evaluation.setCandidateResponse(0, 3);
		assertEquals(evaluation.getQuestions().get(0).getOption(3), evaluation.getCandidateResponse(0));
	}
	
	@Test
	public void testGetInvalidCandidateResponse() {
		evaluation.setCandidateResponse(2, 4);
		assertNull(evaluation.getCandidateResponse(17));
	}
	
	@Test
	public void testNormalGetQuantityOfQuestionsInThisEvaluation() {
		assertEquals(Integer.valueOf(3), evaluation.getQuantityOfQuestionsInThisEvaluation());
	}
	
	@Test
	public void testZeroGetQuantityOfQuestionInThisEvaluation() {
		Evaluation evaluationZeroQuestion = new Evaluation();
		evaluationZeroQuestion.setQuestions(new ArrayList<Question>());
		assertEquals(Integer.valueOf(0), evaluationZeroQuestion.getQuantityOfQuestionsInThisEvaluation());
	}
	
	@Test
	public void testCalculateResult() {
		evaluation.setCandidateResponse(0, 0);
		evaluation.setCandidateResponse(1, 4);
		evaluation.setCandidateResponse(2, 2);
		evaluation.calculateResult();
		assertEquals(Integer.valueOf(2), evaluation.getResult());
	}

	@Test
	public void testCalculateResultAllResponseAreCorrect() {
		evaluation.setCandidateResponse(0, 0);
		evaluation.setCandidateResponse(1, 1);
		evaluation.setCandidateResponse(2, 2);
		evaluation.calculateResult();
		assertEquals(Integer.valueOf(3), evaluation.getResult());
	}
	
	@Test
	public void testCalculateResultAllResponseAreIncorrect() {
		evaluation.setCandidateResponse(0, 2);
		evaluation.setCandidateResponse(1, 4);
		evaluation.setCandidateResponse(2, 4);
		evaluation.calculateResult();
		assertEquals(Integer.valueOf(0), evaluation.getResult());
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

	private void runFirstQuestion() {
		Question question = runQuestion("Descobrimento", "Quem descobriu o brasil?", QuestionStatus.ACTIVE, Level.JUNIOR);
		options = new ArrayList<Option>();
		Option correct = runOption("Pedro");
		correct.setCorrect(true);
		options.add(correct);
		options.add(runOption("Pero"));
		options.add(runOption("Vasco"));
		options.add(runOption("Cristovão"));
		options.add(runOption("O Chapolím colorado"));
		question.setOptions(options);
		questions.add(question);
	}

	private void runThirdQuestion() {
		Question question = runQuestion("Melhor time do Mundo", "Qual é o Melhor time do mundo?", QuestionStatus.ACTIVE, Level.JUNIOR);
		options = new ArrayList<Option>();
		options.add(runOption("Barcelona"));
		options.add(runOption("Real Madrid"));
		Option correct = runOption("Milan");
		correct.setCorrect(true);
		options.add(correct);
		options.add(runOption("Manchester United"));
		options.add(runOption("Bayern Munique"));
		question.setOptions(options);
		questions.add(question);
	}

	private void runSecondQuestion() {
		Question question = runQuestion("Linguagem Popular", "Qual a linguagem de programação mais popular?", QuestionStatus.ACTIVE, Level.JUNIOR);
		options = new ArrayList<Option>();
		options.add(runOption("C/C++"));
		Option correct = runOption("Java");
		correct.setCorrect(true);
		options.add(correct);
		options.add(runOption("Php"));
		options.add(runOption("C#"));
		options.add(runOption("Objective C"));
		question.setOptions(options);
		questions.add(question);
	}

	
	
//calculateResult, isActive, 	
}
