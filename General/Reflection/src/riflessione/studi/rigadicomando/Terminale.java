package riflessione.studi.rigadicomando;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import riflessione.studi.rigadicomando.configs.Parametro;

public class Terminale {
	// melhorar com path
	final String PACKAGE = "riflessione.studi.rigadicomando.comandos.";
	private Class<?> classe;

	public Object fare(String comando) throws Exception {
		try {
			String[] commandEParametro = comando.split(" ", 2);
			String nomeDellaClasse = commandEParametro[0].substring(0,1).toUpperCase() + commandEParametro[0].substring(1);
			String parametros = commandEParametro[1];
			importazione(nomeDellaClasse);
			
			Object istanza = classe.newInstance();
			
			for( Field campo : classe.getDeclaredFields() ) {
				Parametro parametroAnnotazione = campo.getAnnotation(Parametro.class);
				String valore = ottenereParametroDellaRigaLinea(parametros, parametroAnnotazione.value());
				if( !parametroAnnotazione.opzionale() && valore == null ) {
					throw new Exception( String.format("Il campo %s(%s) è obbligatorio", campo.getName(), parametroAnnotazione.value()) );
				}
				
				impostaValoreInCampo( istanza, campo, valore );
			}
			
			return istanza;
		} catch (ClassNotFoundException e) {
			System.out.println("nao achou");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void impostaValoreInCampo(Object istanza, Field campo, String valore) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method metodoSetter = diCampoPerSetter(istanza.getClass(), campo);
		if( campo.getType().equals(String.class) ) {
			metodoSetter.invoke(istanza, valore);
		} else if( campoHaValueOf(campo) ) {
			Object valoreConvertito = campo.getType().getDeclaredMethod("valueOf", String.class).invoke(null, valore);
			metodoSetter.invoke(istanza, valoreConvertito);
		} else {
			Object valoreConvertito = convertireOggettoPersonalizzato(campo.getType(), valore);
			metodoSetter.invoke(istanza, valoreConvertito);
		}
	}

	private Object convertireOggettoPersonalizzato(Class<?> tipo, String valore) {
		if( tipo.equals(Date.class) ) {
			String[] parteDiData = valore.split("/");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, Integer.valueOf(parteDiData[0]));
			calendar.add(Calendar.MONTH, 		Integer.valueOf(parteDiData[1]));
			calendar.add(Calendar.YEAR, 		Integer.valueOf(parteDiData[2]));
			return calendar.getTime();
		} else if( tipo.equals(List.class) ) {
			List<Object> listaDiValori = new ArrayList<Object>();
			for( String valoreDellaLista : valore.split(",") ) {
				listaDiValori.add( valoreDellaLista.trim() );
			}
			return listaDiValori;
		}
		return null;
	}

	private Method diCampoPerSetter(Class<? extends Object> classe, Field campo) throws NoSuchMethodException, SecurityException {
		String nomeDellMetodo = "set" + campo.getName().substring(0,1).toUpperCase() + campo.getName().substring(1);
		return classe.getDeclaredMethod(nomeDellMetodo, campo.getType());
	}

	private boolean campoHaValueOf(Field campo) {
		try {
			Method metodo = campo.getType().getDeclaredMethod("valueOf", String.class);
			return Modifier.isPublic( metodo.getModifiers() ) && metodo.getReturnType().equals(campo.getType());
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}

	private String ottenereParametroDellaRigaLinea(String tuttiParametri, String parametro) {
		String pattern = "{parametro} (.*?)(\\s-[a-z]|$)".replace("{parametro}", parametro);
		Matcher matcher = Pattern.compile(pattern).matcher(tuttiParametri);
		
		if( !matcher.find() )
			return null;
		
		return matcher.group(1);
	}

	private void importazione(String nomeDellaClasse) throws ClassNotFoundException {
		classe = Class.forName( PACKAGE + nomeDellaClasse);
	}
	
}
