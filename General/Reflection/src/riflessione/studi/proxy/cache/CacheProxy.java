package riflessione.studi.proxy.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import riflessione.studi.proxy.cache.annotations.Cache;
import riflessione.studi.proxy.cache.annotations.InvalidCache;

public class CacheProxy implements InvocationHandler {

	private Object impl;
	
	private Map<String, Map<String, Object>> cache = new HashMap<String, Map<String, Object>>();

	private CacheProxy(Object impl) {
		this.impl = impl;
	}
	
	public static Object createInstance(Object impl) {
		return Proxy.newProxyInstance(
				impl.getClass().getClassLoader(), 
				impl.getClass().getInterfaces(), 
				new CacheProxy(impl)
		);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if( method.isAnnotationPresent(Cache.class) ) {
			String argumentiChiave = argomentiToString(args);
			if( hasValueInCache(method, argumentiChiave) ) {
				System.out.println(String.format("Metodo[%s] Argumento[%s] pegando do cache", method.getName(), argumentiChiave));
				return ottenereValoreDelCache(method.getName(), argumentiChiave);
			} else {
				System.out.println(String.format("Metodo[%s] Argumento[%s] não esta no cache", method.getName(), argumentiChiave));
				Object valore = method.invoke(impl, args);
				mettereIlValoreNelCache(method.getName(), argumentiChiave, valore);
				return valore;
			}
		}
		
		if( method.isAnnotationPresent(InvalidCache.class) ) {
			cache.clear();
			return method.invoke(impl, args);
		}

		System.out.println("Metodo sem cache configurado");
		return method.invoke(impl, args);
	}

	private Object ottenereValoreDelCache(String nomeDelMetodo, String argumentiChiave) {
		return cache.get(nomeDelMetodo).get(argumentiChiave);
	}

	private void mettereIlValoreNelCache(String nomeDelMetodo, String argumentiChiave, Object valore) {
		if( ! cache.containsKey(nomeDelMetodo) ) {
			cache.put(nomeDelMetodo, new HashMap<String, Object>());
		}
		
		cache.get(nomeDelMetodo).put(argumentiChiave, valore);
	}

	private boolean hasValueInCache(Method method, String argumentiChiave) {
		String nomeDelMetodo = method.getName();
		if( ! cache.containsKey(nomeDelMetodo) ) {
			return false;
		}
		
		if( ! cache.get(nomeDelMetodo).containsKey(argumentiChiave) ) {
			return false;
		}
		
		return true;
	}

	private String argomentiToString(Object[] args) {
		StringBuilder chiave = new StringBuilder();
		
		Object[] argumenti = (Object[]) args[0]; 
		
		for( Object argomento : argumenti ) {
			chiave.append(argomento).append("|");
		}
		
		return chiave.toString();
	}


}
