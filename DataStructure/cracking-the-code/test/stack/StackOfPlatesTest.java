package stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("StackOfPlates")
public class StackOfPlatesTest {

	@Nested
	@DisplayName("when push")
	class Push {
		@DisplayName("with one item must have one stack with one element")
		@Test
		public void case_1() {
			StackOfPlates stack = new StackOfPlates();
			stack.push("a");
			
			Stack<Stack<String>> set = getSetOfStacks(stack);
			assertEquals(1, set.size());
			
			assertEquals(1, set.get(0).size());
			assertEquals("a", set.get(0).get(0));
		}
	
		@DisplayName("with over capacity must have two stacks")
		@Test
		public void case_2() {
			StackOfPlates stack = new StackOfPlates();
			stack.push("a");
			stack.push("b");
			stack.push("c");
			stack.push("d");
			stack.push("e");
			stack.push("f");

			Stack<Stack<String>> set = getSetOfStacks(stack);
			assertEquals(2, set.size());
			
			assertEquals(5, set.get(0).size());
			assertEquals(1, set.get(1).size());
			
			assertEquals("a", set.get(0).get(0));
			assertEquals("b", set.get(0).get(1));
			assertEquals("c", set.get(0).get(2));
			assertEquals("d", set.get(0).get(3));
			assertEquals("e", set.get(0).get(4));

			assertEquals("f", set.get(1).get(0));
		}
	}

	@Nested
	@DisplayName("when pop")
	class Pop {
		@DisplayName("with single item must pop the element and stack must be empty")
		@Test
		public void case_one() {
			StackOfPlates stack = stackWithSingleElement();
			String item = stack.pop();
			
			assertEquals("a", item);
			
			Stack<Stack<String>> set = getSetOfStacks(stack);
			assertEquals(0, set.size());
		}
		
		@DisplayName("with single six items must pop the element and remove second stack")
		@Test
		public void case_two() {
			StackOfPlates stack = stackWithSixItemsElements();
			String item = stack.pop();
			
			assertEquals("f", item);
			
			Stack<Stack<String>> set = getSetOfStacks(stack);
			assertEquals(1, set.size());
			assertEquals(5, set.get(0).size());
			
			assertEquals("a", set.get(0).get(0));
			assertEquals("b", set.get(0).get(1));
			assertEquals("c", set.get(0).get(2));
			assertEquals("d", set.get(0).get(3));
			assertEquals("e", set.get(0).get(4));
		}
		
		@DisplayName("with two items must pop the element and let the other")
		@Test
		public void case_three() {
			StackOfPlates stack = stackWithTwoItemsElements();
			String item = stack.pop();
			
			assertEquals("b", item);
			
			Stack<Stack<String>> set = getSetOfStacks(stack);
			assertEquals(1, set.size());
			assertEquals(1, set.get(0).size());
			
			assertEquals("a", set.get(0).get(0));
		}
		
	}

	@Nested
	@DisplayName("when pop at")
	class PopAt {
		
	}
	
	@SuppressWarnings("unchecked")
	private Stack<Stack<String>> getSetOfStacks(StackOfPlates stack) {
		return (Stack<Stack<String>>) ReflectionTestUtils.getField(stack, "set");
	}
	
	private StackOfPlates stackWithSingleElement() {
		StackOfPlates stack = new StackOfPlates();
		stack.push("a");
		return stack;
	}
	private StackOfPlates stackWithSixItemsElements() {
		StackOfPlates stack = new StackOfPlates();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.push("d");
		stack.push("e");
		stack.push("f");
		return stack;
	}
	private StackOfPlates stackWithTwoItemsElements() {
		StackOfPlates stack = new StackOfPlates();
		stack.push("a");
		stack.push("b");
		return stack;
	}
}
