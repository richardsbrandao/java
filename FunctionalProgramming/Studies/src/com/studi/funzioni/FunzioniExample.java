package com.studi.funzioni;

import java.util.function.Function;

public class FunzioniExample {

	public static void main(String[] args) {
		Function<Integer, Integer> add1 = x -> x + 1;
		Function<String, String> hello = x -> "Hello " + x;
		
		System.out.println(add1.apply(3));
		System.out.println(hello.apply("Richard"));
	}
	
}
