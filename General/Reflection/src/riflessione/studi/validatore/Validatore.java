package riflessione.studi.validatore;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import riflessione.studi.validatore.annotazione.Gamma;
import riflessione.studi.validatore.annotazione.NonInBianco;
import riflessione.studi.validatore.annotazione.StringMatcher;
import riflessione.studi.validatore.type.TipoValidatore;

public class Validatore {

	public List<String> covalidare(Object obj) throws Exception {
		List<String> errori = new ArrayList<String>();
		
		Class<? extends Object> classeAValidare = obj.getClass();
		
		for( Field campo : classeAValidare.getDeclaredFields() ) {
			for( Annotation annotazione : campo.getDeclaredAnnotations() ) {
				TipoValidatore tipoValidatore = ottenereValidatore(annotazione);
				String errore = tipoValidatore.fare( campo, obj );
				if( errore != null ) {
					errori.add(errore);
				}
			}
		}
		
		return errori;
	}

	private TipoValidatore ottenereValidatore(Annotation annotazione) {
		Class<? extends Annotation> tipoAnnotazione = annotazione.annotationType();
		
		if( tipoAnnotazione.equals( NonInBianco.class ) ) {
			return TipoValidatore.factory(TipoValidatore.NON_IN_BIANCO);
		} else if( tipoAnnotazione.equals( Gamma.class ) ) {
			return TipoValidatore.factory(TipoValidatore.GAMMA);
		} else if( tipoAnnotazione.equals( StringMatcher.class ) ) {
			return TipoValidatore.factory(TipoValidatore.STRING_VALIDATORE);
		}
		
		return null;
	}
	
}
