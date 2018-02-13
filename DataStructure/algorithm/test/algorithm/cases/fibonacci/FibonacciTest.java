package algorithm.cases.fibonacci;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithm.cases.fibo.Fibonacci;

public class FibonacciTest {

	@Test
	public void when_0() {
		assertEquals(0L, new Fibonacci().recursive(0));
		assertEquals(0L, new Fibonacci().withLooping(0));
		assertEquals(0L, new Fibonacci().withVector(0));
	}
	
	@Test
	public void when_1() {
		assertEquals(1L, new Fibonacci().recursive(1));
		assertEquals(1L, new Fibonacci().withLooping(1));
		assertEquals(1L, new Fibonacci().withVector(1));
	}
	
	@Test
	public void when_2() {
		assertEquals(1L, new Fibonacci().recursive(2));
		assertEquals(1L, new Fibonacci().withLooping(2));
		assertEquals(1L, new Fibonacci().withVector(2));
	}
	
	@Test
	public void when_n() {
		assertEquals(144L, new Fibonacci().recursive(12));
		assertEquals(144L, new Fibonacci().withLooping(12));
		assertEquals(144L, new Fibonacci().withVector(12));
	}
	
}
