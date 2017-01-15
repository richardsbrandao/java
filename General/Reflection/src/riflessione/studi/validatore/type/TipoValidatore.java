package riflessione.studi.validatore.type;

import java.lang.reflect.Field;

public abstract class TipoValidatore {
	
	public static int NON_IN_BIANCO = 1;
	public static int STRING_VALIDATORE = 2;
	public static int GAMMA = 3;
	
	public static TipoValidatore factory(int type) {
		switch(type) {
			case 1:
				return new NonInBiancoValidatore();
			case 2:
				return new StringValidatore();
			case 3: 
				return new GammaValidatore();
		}
		
		return null;
	}
	
	public abstract String fare(Field campo, Object esempio);
	
}
