package creazione.builder;


public interface BillettaBuilder {

	BillettaBuilder construireTrattario(String trattario); //Sacado
	BillettaBuilder construireCedente(String cedente); //cedente
	BillettaBuilder construireValore(Double valore); //valor
	BillettaBuilder construireMaturita(); //vencimento
	BillettaBuilder construireNostroNumero(); //nossoNumero
	BillettaBuilder construireBarcode(); //Codigo de Barra
	BillettaBuilder construireLogo(); //Logo
	
	Billetta getBilletta();
	
}
