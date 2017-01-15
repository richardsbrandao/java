package com.richard.utils;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UpdateUtils {

	private static Log LOG = LogFactory.getLog( UpdateUtils.class );
	
	public static void mergeProperties(Class<? extends Object> clazz, Object targetObject, Object newObjectValues, String[] whiteList) {
		Method[] methods = clazz.getDeclaredMethods();
		for( String whiteField : whiteList ) {
			String setMethodName = generateSetMethodName( whiteField );
			String getMethodName = generateGetMethodName( whiteField );
			
			Method setMethod = getMethodOn(methods, setMethodName);
			Method getMethod = getMethodOn(methods, getMethodName);
			
			mergePropertyInWithGetterAndSetterMethods(targetObject, setMethod, newObjectValues, getMethod);
		}
	}

	private static String generateGetMethodName(String name) {
		return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	private static Method getMethodOn(Method[] methods, String methodName) {
		for( Method method : methods ) {
			if( method.getName().equals( methodName ) ) {
				return method;
			}
		}
		throw new NoSuchMethodError();
	}

	private static String generateSetMethodName(String name) {
		return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	private static void mergePropertyInWithGetterAndSetterMethods(Object targetObject, Method setMethod, Object newObjectValues, Method getMethod) {
		try {
			Object newValue = getMethod.invoke(newObjectValues, new Object[] {});
			if( newValue != null ) {
				setMethod.invoke(targetObject, new Object[] { newValue });
			}
		} catch ( Exception e ) {
			LOG.debug( "Erro ao fazer merge da propriedade utilizando o metodo " + setMethod.getName(), e );
		}
	}
	
}
