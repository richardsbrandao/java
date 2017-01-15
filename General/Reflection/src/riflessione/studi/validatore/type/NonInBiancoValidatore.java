package riflessione.studi.validatore.type;

import java.lang.reflect.Field;

public class NonInBiancoValidatore extends TipoValidatore {

	@Override
	public String fare(Field campo, Object esempio) {
		try {
			Object valore = campo.get(esempio);
			
			if( valore == null ) {
				return String.format("O campo %s não pode ser nulo", campo.getName());
			}
			
			if( String.class.equals( valore.getClass() ) ) {
				String valoreStr = (String) valore;
				
				if( "".equals(valoreStr) ) {
					return String.format("O campo %s não pode ser vazio", campo.getName());
				}
			}
			
			return null;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return String.format("Erro com o campo %s: %s", campo.getName(), e.getMessage());
		}
	}

}
