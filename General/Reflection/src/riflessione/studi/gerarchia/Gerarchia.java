package riflessione.studi.gerarchia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gerarchia {

	public Class<?> importazione(String nomeDellaClasse) throws ClassNotFoundException {
		Class<?> classe = Class.forName(nomeDellaClasse);
		System.out.println("Classe encontrada");
		return classe;
	}

	public void stampa(Class<?> classe, int livello) {
		List<Class<?>> superClasses = ottenereSuperClasse(classe);
		String retiro = "";
		for(int i = 0; i < livello; i++) {
			retiro += "   ";
		}
		
		for( Class<?> superClasse : superClasses ) {
			System.out.println( retiro + simbolo(superClasse) + superClasse.getName() );
			if( superClasse != Object.class ) {
				stampa( superClasse, livello+1 );
			}
		}
	}

	private String simbolo(Class<?> superClasse) {
		return superClasse.isInterface() ? " |-> " : " @->";
	}

	private List<Class<?>> ottenereSuperClasse(Class<?> classe) {
		List<Class<?>> superClasses = new ArrayList<Class<?>>();
		
		if( classe.getSuperclass() != null ) {
			superClasses.add( classe.getSuperclass() );
		}
		
		if( classe.getInterfaces().length > 0 ) {
			superClasses.addAll( Arrays.asList(classe.getInterfaces()) );
		}
		
		return superClasses;
	}
	
}
