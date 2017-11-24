package algorithm.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class MinimumStackValueTest {

	@Test
	public void test_minimum_stack_case_1() {
		MinimumStackValue<Integer> stack = new MinimumStackValue<Integer>();
		stack.push(1);
		stack.push(4);
		stack.push(2);
		stack.push(7);
		stack.push(5);
		assertEquals(Integer.valueOf(1), stack.minimum());
	}
	
	@Test
	public void test_minimum_stack_case_2() {
		MinimumStackValue<Integer> stack = new MinimumStackValue<Integer>();
		stack.push(2);
		stack.push(4);
		stack.push(1);
		stack.push(7);
		stack.pop();
		assertEquals(Integer.valueOf(1), stack.minimum());
		stack.pop();
		assertEquals(Integer.valueOf(2), stack.minimum());
	}

	@Test
	public void test_minimum_stack_case_3() {
		MinimumStackValue<Integer> stack = new MinimumStackValue<Integer>();
		stack.push(7);
		stack.push(4);
		stack.push(2);
		stack.push(7);
		stack.push(1);
		assertEquals(Integer.valueOf(1), stack.minimum());
	}

	@Test
	public void test_minimum_stack_case_4() {
		MinimumStackValue<Integer> stack = new MinimumStackValue<Integer>();
		assertNull(stack.minimum());
	}
}
