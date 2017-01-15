package riflessione.studi.validatore.type;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import riflessione.studi.validatore.annotazione.StringMatcher;


public class StringValidatore extends TipoValidatore {

	@Override
	public String fare(Field campo, Object esempio) {
		StringMatcher annotazione = campo.getAnnotation(StringMatcher.class);
		try {
			Object valore = campo.get(esempio);
			
			if( valore == null ) {
				return null;
			}
			
			String valoreStr = (String) valore;
			if( annotazione.lunghezza() > -1 ) {
				if( valoreStr.length() != annotazione.lunghezza() ) {
					return String.format("O campo %s deve ter %d caracteres", campo.getName(), annotazione.lunghezza());
				}
			}
			
			if( ! "".equals(annotazione.modello()) ) {
				if( !Pattern.compile(annotazione.modello()).matcher(valoreStr).matches() ) {
					return String.format("O campo %s não está no padrão correto", campo.getName());
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return String.format("Erro com o campo %s: %s", campo.getName(), e.getMessage());
		}
		
		return null;
	}

}
