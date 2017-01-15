package creazione.factory;

public class JMSRilascio extends Rilascio {

	@Override
	public void enviare() {
		System.out.println("Invia un messaggio via JMS");
	}

}
