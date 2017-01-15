package riflessione.studi.validatore.type;

import java.lang.reflect.Field;

import riflessione.studi.validatore.annotazione.Gamma;

public class GammaValidatore extends TipoValidatore {

	@Override
	public String fare(Field campo, Object esempio) {
		try {
			Integer valoreIntero = (Integer) campo.get(esempio);
			Gamma gamma = campo.getAnnotation(Gamma.class);
			
			if( gamma.minimo() > valoreIntero ) {
				return String.format("O campo %s deve ser maior que %d", campo.getName(), gamma.minimo());
			}
			
			if( gamma.massimo() < valoreIntero ) {
				return String.format("O campo %s deve ser menor que %d", campo.getName(), gamma.massimo());
			}
			
			return null;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return String.format("Erro com o campo %s: %s", campo.getName(), e.getMessage());
		}
	}

}
