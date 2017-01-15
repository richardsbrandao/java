package riflessione.studi.validatore.esempio;

import riflessione.studi.validatore.annotazione.Gamma;
import riflessione.studi.validatore.annotazione.NonInBianco;
import riflessione.studi.validatore.annotazione.StringMatcher;

public class CartaDiCredito {
	
	@NonInBianco
	public String privateLabel;
	
	@StringMatcher(lunghezza=16, modello="^[0-9]{16}$")
	@NonInBianco
	public String numero;
	
	@NonInBianco
	@Gamma(minimo=1500, massimo=10000)
	public Integer limite;

	public CartaDiCredito(String privateLabel, String numero, Integer limite) {
		this.privateLabel = privateLabel;
		this.numero = numero;
		this.limite = limite;
	}
	
}
