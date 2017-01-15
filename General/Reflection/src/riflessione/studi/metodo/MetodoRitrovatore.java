package riflessione.studi.metodo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetodoRitrovatore {

	private Class<?> classe;
	private Method metodo;
	private Map<Integer, Constructor<?>> construttori;
	private Constructor<?> construttore; 
	
	public MetodoRitrovatore(String nomeDellaClasse, String nomeDelMetodo) throws ClassNotFoundException {
		classe = Class.forName(nomeDellaClasse);
		metodo = trovareMetodoPerNome(nomeDelMetodo);
		impostareConstruttori();
	}	
	
	private void impostareConstruttori() {
		construttori = new HashMap<Integer, Constructor<?>>();
		Integer index = 0;
		
		for( Constructor<?> construttore : classe.getConstructors() ) {
			construttori.put(index++, construttore);
		}
	}

	public Method trovareMetodoPerNome(String nomeDelMetodo) {
		for( Method metodo : classe.getMethods() ) {
			if( metodo.getName().equals(nomeDelMetodo) ) {
				return metodo;
			}
		}
		
		throw new IllegalArgumentException("Nome del metodo invalido!");
	}

	public int lunghezzaParametri() {
		return metodo.getParameterCount();
	}

	public Object invocare(List<Object> construttoreParametri, List<Object> metodoParametri) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		Object obj = construttore.newInstance(construttoreParametri.toArray());
		Object[] parametri = metodoParametri.toArray();
		Object[] turnoTipos = new Object[parametri.length];
		
		for( int i = 0; i < parametri.length; i++ ) {
			if( metodo.getParameterTypes()[i].getClass().equals(String.class) ) {
				turnoTipos[i] = parametri[i];
			} else {
				turnoTipos[i] = convertire(parametri[i], metodo.getParameterTypes()[i]);
//				turnoTipos[i] = metodo.getParameterTypes()[i].getDeclaredMethod("valueOf", String.class).invoke(null, parametri[i]);
			}
		}
		
		return metodo.invoke(obj, turnoTipos);
	}

	private Object convertire(Object valore, Class<?> classe) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return classe.getDeclaredMethod("valueOf", String.class).invoke(null, valore);
	}

	public void scrivereLaListaDelConstrutore() {
		construttori.forEach((k, v) -> {
			System.out.println(k + ": " );
			
			for(  Parameter parametro : v.getParameters() ) {
				String format = String.format("%s: %s", parametro.getName(), parametro.getType().getSimpleName());
				System.out.println( format );
			}
		});
	}

	public int lunghezzaDelConstruttoreParametri() {
		return construttore.getParameterCount();
	}

	public void impostaConstruttore(Integer index) {
		construttore = construttori.get(index);
	}
	
	
}
