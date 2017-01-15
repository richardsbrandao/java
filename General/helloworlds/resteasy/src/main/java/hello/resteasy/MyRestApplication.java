package hello.resteasy;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class MyRestApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public MyRestApplication() {
		singletons.add(new RESTEasyHelloWorldService());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
}
