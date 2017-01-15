package creazione.builder;

public class Main {

	public static void main(String[] args) {
		ItauBillettaBuilder itauBillettaBuilder = new ItauBillettaBuilder();
		BilletGenerator itauBilletGenerator = new BilletGenerator(itauBillettaBuilder);
		Billetta itauBilletta = itauBilletGenerator.generareBilletta("Richards Ltda", "Richard Brandão", 100D);
		System.out.println( itauBilletta );
		

		BBBillettaBuilder bbBillettaBuilder = new BBBillettaBuilder();
		BilletGenerator bbBilletGenerator = new BilletGenerator(bbBillettaBuilder);
		Billetta bbBilletta = bbBilletGenerator.generareBilletta("MaxiConta", "Ketherin Felix", 400D);
		System.out.println( bbBilletta );
		

		BradescoBillettaBuilder bradescoBillettaBuilder = new BradescoBillettaBuilder();
		BilletGenerator billetGenerator = new BilletGenerator(bradescoBillettaBuilder);
		Billetta bradescoBilletta = billetGenerator.generareBilletta("Telemar!! rsrs", "Carlos Brandão", 100D);
		System.out.println( bradescoBilletta );
	}

}
