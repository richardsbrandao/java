package riflessione.studi.proxy.appconfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AppConfigProxy implements InvocationHandler {

	public static Object createInstance() {
		return Proxy.newProxyInstance(
					Config.class.getClassLoader(), 
					new Class[]{Config.class}, 
					new AppConfigProxy()
				);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String property = nameToProperty(method.getName());
		String value = getProperty(property);
		return castValue(value, method.getReturnType());
	}

	private Object castValue(String value, Class<? extends Object> typeToCast) {
		if(typeToCast.equals(String.class)) {
			return value;
		}
		
		try {
			Method method = typeToCast.getMethod("valueOf", String.class);
			return method.invoke(null, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String getProperty(String property) {
		return System.getProperty(property);
	}

	private String nameToProperty(String name) {
		name = name.replace("get", "");
		
		StringBuilder sb = new StringBuilder( String.valueOf(Character.toLowerCase(name.charAt(0))) );
		for(int i = 1; i < name.length(); i++) {
			char c = name.charAt(i);
			
			if( Character.isUpperCase( c ) ) {
				sb.append(".").append( Character.toLowerCase(c) );
				continue;
			}
			
			sb.append(c);
		}
		
		return sb.toString();
	}

}
