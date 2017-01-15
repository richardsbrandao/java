package riflessione.test.proxy.cache;

import riflessione.studi.proxy.cache.CacheProxy;
import riflessione.studi.proxy.cache.Calcolatrice;
import riflessione.studi.proxy.cache.Operazione;

public class CacheProxyTest {

	public static void main(String[] args) {
		Operazione calcolatriceConCacheProxy = (Operazione) CacheProxy.createInstance( new Calcolatrice() );
		
		String risultato = "Il Risultato è: %s";
		
		int somma = calcolatriceConCacheProxy.somma(8, 9);
		System.out.println(String.format(risultato, somma));
		
		somma = calcolatriceConCacheProxy.somma(10, 2);
		System.out.println(String.format(risultato, somma));
		
		somma = calcolatriceConCacheProxy.somma(8, 9, 4);
		System.out.println(String.format(risultato, somma));
		
		somma = calcolatriceConCacheProxy.somma(8, 9);
		System.out.println(String.format(risultato, somma));
		
		somma = calcolatriceConCacheProxy.somma(9, 8);
		System.out.println(String.format(risultato, somma));
		
		int moltiplicazione = calcolatriceConCacheProxy.moltiplicazione(4,8);
		System.out.println(String.format(risultato, moltiplicazione));
		
		moltiplicazione = calcolatriceConCacheProxy.moltiplicazione(4,8);
		System.out.println(String.format(risultato, moltiplicazione));
		
		calcolatriceConCacheProxy.infirmareCache();
		
		somma = calcolatriceConCacheProxy.somma(9, 8);
		System.out.println(String.format(risultato, somma));
	}
	
}
