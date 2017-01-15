package riflessione.test.proxy.async;

import riflessione.studi.proxy.async.AsyncProxy;
import riflessione.studi.proxy.async.NotifyService;
import riflessione.studi.proxy.async.NotifyServiceImpl;


public class AsyncProxyTest {

	public static void main(String[] args) {
		NotifyService proxy = (NotifyService) AsyncProxy.createInstance(new NotifyServiceImpl());
		proxy.sendEmail("Richard");
	}
	
}
