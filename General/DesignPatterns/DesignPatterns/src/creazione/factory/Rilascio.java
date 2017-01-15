package creazione.factory;

public abstract class Rilascio {

	public static int EMAIL = 0;
	public static int JMS = 1;
	public static int SMS = 2;
	
	public static Rilascio rilascioFactory(int tipoDiMessaggio) {
		if( tipoDiMessaggio == EMAIL ) {
			return new EmailRilascio();
		} else if( tipoDiMessaggio == SMS ) {
			return new SMSRilascio();
		}
		return new JMSRilascio();
	}
	
	public abstract void enviare();
	
}
