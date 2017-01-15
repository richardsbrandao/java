package creazione.factory;

public class SMSRilascio extends Rilascio {

	@Override
	public void enviare() {
		System.out.println("Invia un messaggio via SMS");
	}

}
