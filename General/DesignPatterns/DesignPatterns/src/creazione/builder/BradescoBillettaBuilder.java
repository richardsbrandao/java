package creazione.builder;

import java.util.Calendar;

public class BradescoBillettaBuilder implements BillettaBuilder {

	private Billetta billetta;
	
	public BradescoBillettaBuilder() {
		billetta = new Billetta();
	}

	@Override
	public BillettaBuilder construireTrattario(String trattario) {
		billetta.setTrattario(trattario);
		return this;
	}

	@Override
	public BillettaBuilder construireCedente(String cedente) {
		billetta.setCedente(cedente);
		return this;
	}

	@Override
	public BillettaBuilder construireValore(Double valore) {
		billetta.setValore(valore);
		return this;
	}

	@Override
	public BillettaBuilder construireMaturita() {
		Calendar maturita = Calendar.getInstance();
		maturita.add(Calendar.DAY_OF_MONTH, 5);
		billetta.setMaturita(maturita);
		return this;
	}

	@Override
	public BillettaBuilder construireNostroNumero() {
		billetta.setNostroNumero("33333333333333");
		return this;
	}

	@Override
	public BillettaBuilder construireBarcode() {
		double barcode = Math.random() * 10000;
		billetta.setBarcode( String.valueOf(barcode) );
		return this;
	}

	@Override
	public BillettaBuilder construireLogo() {
		billetta.setLogo("Bradesco");
		return this;
	}

	@Override
	public Billetta getBilletta() {
		return billetta;
	}

}
