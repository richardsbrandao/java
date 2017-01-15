package riflessione.studi.rmapper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import riflessione.studi.rmapper.uzansa.RIgnorare;
import riflessione.studi.rmapper.uzansa.RProprieta;

public class RMapper {
	
	public static Map<String, Object> classePerMapa(Object oggetto) throws Exception {
		Map<String, Object> tuttiLeProprieta = new HashMap<String, Object>();
		
		Class<? extends Object> classe = oggetto.getClass();
		
		for( Method metodo : classe.getMethods() ) {
			if( ehGetter(metodo) ) {
				String proprieta = ottenereNomeDellaProprieta(metodo);
				Object risultado = metodo.invoke(oggetto);
				tuttiLeProprieta.put(proprieta, risultado);
			}
		}
		
		return tuttiLeProprieta;
	}

	private static String ottenereNomeDellaProprieta(Method metodo) {
		if( ! metodo.isAnnotationPresent( RProprieta.class ) ) {
			return ottenereNomeDalMetodo(metodo);
		}
		
		return ottenereNomeDaAnnotazione(metodo);
	}

	private static String ottenereNomeDaAnnotazione(Method metodo) {
		return metodo.getAnnotation(RProprieta.class).nome();
	}

	private static String ottenereNomeDalMetodo(Method metodo) {
		return metodo.getName().substring(3,4).toLowerCase() + metodo.getName().substring(4);
	}

	private static boolean ehGetter(Method metodo) {
		return metodo.getName().startsWith("get") 
					&& metodo.getReturnType() != Void.class 
					&& metodo.getParameterCount() == 0
					&& !metodo.getName().equals("getClass")
					&& !metodo.isAnnotationPresent(RIgnorare.class);
	}
	
}
