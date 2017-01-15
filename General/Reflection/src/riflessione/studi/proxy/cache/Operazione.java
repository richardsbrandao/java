package riflessione.studi.proxy.cache;

public interface Operazione {

	int somma(Integer... numeri);
	
	int moltiplicazione(Integer... numeri);
	
	void infirmareCache();
	
}
