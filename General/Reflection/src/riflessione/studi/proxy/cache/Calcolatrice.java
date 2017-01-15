package riflessione.studi.proxy.cache;

import riflessione.studi.proxy.cache.annotations.Cache;
import riflessione.studi.proxy.cache.annotations.InvalidCache;

public class Calcolatrice implements Operazione {

	@Override
	@Cache
	public int somma(Integer... numeri) {
		System.out.println("Realizando soma ...");
		int risultato = 0;
		
		for( Integer numero : numeri ) {
			risultato += numero;
		}
		
		return risultato;
	}
	
	@Override
	public int moltiplicazione(Integer... numeri) {
		System.out.print("Realizando multiplicação ...");
		int risultado = 1;
		
		for( Integer numero : numeri ) {
			risultado *= numero;
		}
		
		return risultado;
	}
	
	@InvalidCache
	public void infirmareCache() {
		System.out.println("Cache Limpo");
	}

}
