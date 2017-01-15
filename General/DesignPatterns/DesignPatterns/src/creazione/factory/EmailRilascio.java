package creazione.factory;

public class EmailRilascio extends Rilascio {

	@Override
	public void enviare() {
		System.out.println("Invia un messaggio via Email");
	}

}
