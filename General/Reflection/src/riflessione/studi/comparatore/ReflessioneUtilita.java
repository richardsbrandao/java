package riflessione.studi.comparatore;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import riflessione.studi.comparatore.biblioteca.Profondo;

public class ReflessioneUtilita {

	public static <T extends Object> Field[] ottenereCampoDichiarato( T oggetto ) {
		return oggetto.getClass().getDeclaredFields();
	}

	public static Boolean ehPresenteLAnnotazione(Field campo, Class<? extends Annotation> annotazione) {
		campo.isAnnotationPresent(Profondo.class);
		return campo.isAnnotationPresent(annotazione);
	}

	public static Object convertireValore(Object valoreBase, Class<?> o) throws Exception {
		return o.cast(valoreBase);
	}
	
	public static <T> Object ottenereValoreDaGetter(Field campo, Object oggettoDiBase) throws Exception {
		String nomeDaGetter = "get" + campo.getName().substring(0,1).toUpperCase() + campo.getName().substring(1);
		return oggettoDiBase.getClass().getDeclaredMethod(nomeDaGetter).invoke(oggettoDiBase);
	}

	public static String ottenereSempliceNomeDellaClasse(Object oggetto) {
		return oggetto.getClass().getSimpleName();
	}
	
}
