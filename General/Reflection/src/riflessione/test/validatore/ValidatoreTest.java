package riflessione.test.validatore;

import java.util.List;

import riflessione.studi.validatore.Validatore;
import riflessione.studi.validatore.esempio.CartaDiCredito;

public class ValidatoreTest {

	public static void main(String[] args) throws Exception {
		Validatore validatore = new Validatore();
		
		nessunErrore(validatore, 	new CartaDiCredito("Itau", "2332454590901010", 3500));
		nonBiancoErrore(validatore, new CartaDiCredito(null, "2332454590901010", 3500));
		gammaErrore(validatore, 	new CartaDiCredito("Itau", "2332454590901010", 100000));
		lunghezaErrore(validatore, 	new CartaDiCredito("Itau", "233245459090101", 3500));
		modelloErrore(validatore, 	new CartaDiCredito("Itau", "233245459090A010", 3500));
	}

	private static void modelloErrore(Validatore validatore, CartaDiCredito cartaDiCredito) throws Exception {
		List<String> errors = validatore.covalidare(cartaDiCredito);
		System.out.println("ModelloStringErrore");
		errors.forEach((error) -> System.out.println("Erro: " + error) );
	}

	private static void lunghezaErrore(Validatore validatore, CartaDiCredito cartaDiCredito) throws Exception {
		List<String> errors = validatore.covalidare(cartaDiCredito);
		System.out.println("LunghezzaStringErrore");
		errors.forEach((error) -> System.out.println("Erro: " + error) );
	}

	private static void gammaErrore(Validatore validatore, CartaDiCredito cartaDiCredito) throws Exception {
		List<String> errors = validatore.covalidare(cartaDiCredito);
		System.out.println("GammaErrore");
		errors.forEach((error) -> System.out.println("Erro: " + error) );
	}

	private static void nonBiancoErrore(Validatore validatore, CartaDiCredito cartaDiCredito) throws Exception {
		List<String> errors = validatore.covalidare(cartaDiCredito);
		System.out.println("NonBiancoErrore");
		errors.forEach((error) -> System.out.println("Erro: " + error) );
	}

	private static void nessunErrore(Validatore validatore, CartaDiCredito cartaDiCredito) throws Exception {
		List<String> errors = validatore.covalidare(cartaDiCredito);
		System.out.println("NessunErrore");
		errors.forEach((error) -> System.out.println("Erro: " + error) );
	}
	
}
