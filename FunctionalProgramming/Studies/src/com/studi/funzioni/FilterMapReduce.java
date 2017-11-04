package com.studi.funzioni;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterMapReduce {

	public static void main(String[] args) {
		List<String> words = Arrays.asList(
				"Proprio quello che io ho bisogno", 
				"Magari siamo capace di arrivare", 
				"Un Invito meraviglioso", 
				"Io ho molta Memoria di quello momento", 
				"Avremo fortuna di avere tu"
		);
		
		Predicate<? super String> paroleConPiuDiDueLettere = parola -> parola.length() > 2;
		
		Function<? super String, ? extends List<String>> setenzaDaUnElencoDiParole = setenza -> Arrays.asList(setenza.split(" ")).stream().filter(paroleConPiuDiDueLettere).collect(Collectors.toList());
		
		HashMap<Integer, Integer> reduce = words.stream()
			.map(setenzaDaUnElencoDiParole)
			.reduce(
					new HashMap<Integer, Integer>(),
					(mapa, listaDiParole) -> {
						if( mapa.get(listaDiParole.size()) != null ) {
							mapa.put(listaDiParole.size(), mapa.get(listaDiParole.size())+1);
						} else {
							mapa.put(listaDiParole.size(), 1);
						}
						return mapa;
					},
					(a, b) -> a
			);

		System.out.println(reduce);
	}
	
}
