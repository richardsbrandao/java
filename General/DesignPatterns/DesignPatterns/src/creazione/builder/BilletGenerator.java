package creazione.builder;


public class BilletGenerator {
	
	private BillettaBuilder builder;

	public BilletGenerator(BillettaBuilder builder) {
		this.builder = builder;
	}
	
	public Billetta generareBilletta(String cedente, String trattario, Double valore) {
		return builder.construireCedente(cedente).construireValore(valore).construireTrattario(trattario).
				construireMaturita().construireNostroNumero().construireBarcode().construireLogo().getBilletta();
	}
	
}
