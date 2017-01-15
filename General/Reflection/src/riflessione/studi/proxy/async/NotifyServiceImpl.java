package riflessione.studi.proxy.async;

public class NotifyServiceImpl implements NotifyService {

	@Override
	public void sendEmail(String name) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { }
		System.out.println("NotifyService: Formatando Email");
		String emailText = String.format("Hello %s!!", name);
		System.out.println("NotifyService: Enviando email ... " + emailText);
	}

}
