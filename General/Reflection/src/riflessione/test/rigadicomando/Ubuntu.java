package riflessione.test.rigadicomando;

import riflessione.studi.rigadicomando.Terminale;

public class Ubuntu {

	public static void main(String[] args) throws Exception {
		Terminale terminale = new Terminale();
		
		Object comando = terminale.fare("persona -t true -s 5000 -n Richard -b 26/11/1991 -f Ketherin, Cristina, Carlos");
		
		System.out.println("Risultado: " + comando);
	}
	
}
