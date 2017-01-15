package riflessione.studi.comparatore;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import riflessione.studi.comparatore.biblioteca.NonConfrontare;
import riflessione.studi.comparatore.biblioteca.Profondo;
import riflessione.studi.comparatore.biblioteca.Tolleranza;

public class ReflessioneComparatore {

	public <T extends Object> List<String> confrontare(T oggettoDiBase, T altroOggetto) {
		List<String> errori = new ArrayList<String>();
		
		if( ! confrotareTipi(oggettoDiBase, altroOggetto) ) {
			return Arrays.asList("Il altro oggetto non è del tipo " + altroOggetto.getClass().getSimpleName());
		}
		
		for( Field campo : ReflessioneUtilita.ottenereCampoDichiarato( oggettoDiBase ) ) {
			if( ReflessioneUtilita.ehPresenteLAnnotazione(campo, NonConfrontare.class) ) {
				continue;
			}
			
			if( ReflessioneUtilita.ehPresenteLAnnotazione(campo, Profondo.class) ) {
				List<String> analisiApprofondita = analisiApprofondita(oggettoDiBase, altroOggetto, campo);
				
				if( ! analisiApprofondita.isEmpty() ) {
					errori.addAll( analisiApprofondita );
				}
				
				continue;
			}
			
			if( ReflessioneUtilita.ehPresenteLAnnotazione(campo, Tolleranza.class) ) {
				System.out.println(campo.getName());
				if( ! confrontarePerToleranza(oggettoDiBase, altroOggetto, campo) ) {
					errori.add( String.format("Il campo oggetto %s.%s non è pari ad altro valore per toleranza", oggettoDiBase.getClass().getSimpleName(), campo.getName()) );
				}
				continue;
			}
			
			if( ! confrontareValore(oggettoDiBase, altroOggetto, campo) ) {
				errori.add( String.format("Il campo %s.%s non è completamente pari ad altro valore", oggettoDiBase.getClass().getSimpleName(), campo.getName()) );
			}
		}
		
		return errori;
	}

	private <T> Boolean confrontareValore(T oggettoDiBase, T altroOggetto, Field campo) {
		try {
			Object valoreBase = ReflessioneUtilita.ottenereValoreDaGetter(campo, altroOggetto);
			Object valoreAltroOggetto = ReflessioneUtilita.ottenereValoreDaGetter(campo, oggettoDiBase);
			
			if( ReflessioneUtilita.ottenereSempliceNomeDellaClasse(valoreAltroOggetto).contains("[") && ReflessioneUtilita.ottenereSempliceNomeDellaClasse(oggettoDiBase).contains("[") ) {
				return confrontareArray(valoreBase, valoreAltroOggetto);
			} else {
				return valoreAltroOggetto.equals(valoreBase);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private Boolean confrontareArray(Object valoriBase, Object valoriAltroOggetto) {
		boolean contiene = true;
		
		cercarePerElemento:
		for( Object valoreBase : (Object[]) valoriBase ) {
			for( Object valoreAltroOggetto : (Object[]) valoriAltroOggetto ) {
				if( valoreBase.equals(valoreAltroOggetto) ) {
					continue cercarePerElemento;
				}
			}
			contiene = false;
			break;
		}
		
		return contiene;
	}

	private <T extends Object> List<String> analisiApprofondita(T oggettoDiBase, T altroOggetto, Field campo) {
		try {
			Object valoreBase = ReflessioneUtilita.ottenereValoreDaGetter(campo, oggettoDiBase);
			Object valoreOggetto = ReflessioneUtilita.ottenereValoreDaGetter(campo, altroOggetto);
			return confrontare(valoreBase, valoreOggetto);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<String>();
		}
	}
	
	private <T extends Object> boolean confrontarePerToleranza(T oggettoDiBase, T altroOggetto, Field campo) {
		try {
			Object valoreBase = ReflessioneUtilita.ottenereValoreDaGetter(campo, oggettoDiBase);
			Object altroValore = ReflessioneUtilita.ottenereValoreDaGetter(campo, altroOggetto);
			Double tolleranza = campo.getAnnotation(Tolleranza.class).value();
			
			if( ! confrotareTipi(oggettoDiBase, altroOggetto) ) {
				return false;
			}
			
			if( ! confrontareValore(valoreBase, altroValore, tolleranza) ) {
				return false;
			}
			
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	private boolean confrontareValore(Object valoreBase, Object altroValore, Double tolleranza) {
		try {
			Double valoreDouble = (Double) ReflessioneUtilita.convertireValore(valoreBase, Double.class);
			Double altroValoreDouble = (Double) ReflessioneUtilita.convertireValore(altroValore, Double.class);
			return valoreBase.equals( altroValore ) || Math.abs( valoreDouble - altroValoreDouble ) <= tolleranza;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private <T extends Object> Boolean confrotareTipi(T oggettoDiBase, T altroOggetto) {
		return oggettoDiBase.getClass().equals(altroOggetto.getClass());
	}
	
}
