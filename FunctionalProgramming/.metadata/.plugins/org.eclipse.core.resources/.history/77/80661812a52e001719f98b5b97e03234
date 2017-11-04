package com.studi.funzioni;

import java.util.function.Function;

public class ComposizioneDelleFunzioni {

	public static void main(String[] args) {
		Function<Integer, Function<Integer, Integer>> additrice = x -> y -> x + y;
		Function<Integer, Function<Integer, Integer>> moltiplilcatore = x -> y -> x * y;
		
		Function<Integer, Integer> inserisciUno = additrice.apply(1);
		Function<Integer, Integer> raddoppiatore = moltiplilcatore.apply(2);
		
		System.out.println(inserisciUno.apply(5));
		System.out.println(raddoppiatore.apply(9));
	}
	
}
