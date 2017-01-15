package riflessione.studi.proxy.async;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AsyncProxy implements InvocationHandler {

	private Object impl;

	private AsyncProxy(Object impl) {
		this.impl = impl;
	}
	
	public static Object createInstance(Object impl) {
		return Proxy.newProxyInstance(
				impl.getClass().getClassLoader(), 
				impl.getClass().getInterfaces(), 
				new AsyncProxy(impl)
		);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if( method.getReturnType() == void.class ) {
			System.out.println("Proxy: Invocacao Assincrona [INICIO]");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						method.invoke(impl, args);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}).start();
			System.out.println("Proxy: Invocacao Assincrona [FIM]");
			return null;
		} else {
			return method.invoke(impl, args);
		}
		
	}

}
