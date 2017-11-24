package algorithm.stack;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatchingExpressionTest {

	@Test
	public void test_positive_expression_1() {
		String expression = "(abc)asd{234}[1(13s)]";
		assertTrue(new MatchingExpression(expression).evaluate());
	}
	
	@Test
	public void test_positive_expression_2() {
		String expression = "d(abc){as}d";
		assertTrue(new MatchingExpression(expression).evaluate());
	}

	@Test
	public void test_positive_expression_3() {
		String expression = "[({323}sds)ffff]asd{43s}[aa]";
		assertTrue(new MatchingExpression(expression).evaluate());
	}
	
	@Test
	public void test_negative_expression_1() {
		String expression = "abc)asd{234}[1(13s)]";
		assertTrue(new MatchingExpression(expression).evaluate());
	}
	
	@Test
	public void test_negative_expression_2() {
		String expression = "d(abc){asd";
		assertTrue(new MatchingExpression(expression).evaluate());
	}
	
	@Test
	public void test_negative_expression_3() {
		String expression = "[({323}sds)ffff]asd{43s}aa]";
		assertTrue(new MatchingExpression(expression).evaluate());
	}
		
}
