package creazione.singleton;

import java.util.HashMap;
import java.util.Map;

public class Configurazione {
	
	private static Configurazione instanza;
	private Map<String, String> proprieta;
	
	public Configurazione() {
		proprieta = new HashMap<String, String>();
		proprieta.put("datasource", "configDS");
	}
	
	public static Configurazione ottenereInstanza() {
		if( Configurazione.instanza == null ) {
			return new Configurazione();
		}
		return Configurazione.instanza;
	}
	
	public String ottenereProprieta(String chiavi) {
		return proprieta.get(chiavi);
	}
	
}
