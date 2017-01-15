package br.com.ideais.evaluation.test.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.ideais.evaluation.model.entity.Option;

@RunWith(JUnit4.class)
public class OptionTest {
	
	@Test
	public void testIsCorrectOption() {
		Option option = runOption("Resposta teste", true);
		assertTrue(option.isCorrect());
	}
	@Test
	public void testIsNotCorrectOption() {
		Option option = runOption("Outra resposta", false);
		assertFalse(option.isCorrect());
	}
	
	private Option runOption(String answer, Boolean isCorrect) {
		Option option = new Option();
		option.setAnswer(answer);
		option.setCorrect(isCorrect);
		return option;
	}
}
