package riflessione.test.metodo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import riflessione.studi.metodo.MetodoRitrovatore;


public class MetodoEsecutore {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Classe: ");
		String classe = s.nextLine();
		
		System.out.println("Metodo: ");
		String metodo = s.nextLine();
		
		MetodoRitrovatore ritrovatore = new MetodoRitrovatore(classe, metodo);
		
		ritrovatore.scrivereLaListaDelConstrutore();
		String construttore = s.nextLine();
		ritrovatore.impostaConstruttore( Integer.valueOf(construttore) );
		
		List<Object> construtorriParametri = new ArrayList<Object>();
		for( int i = 0; i < ritrovatore.lunghezzaDelConstruttoreParametri(); i++ ) {
			System.out.println(String.format("Parametro Construttore %d: ", i));
			String a = s.nextLine();
			construtorriParametri.add( a );
		}
		
		List<Object> parametri = new ArrayList<Object>();
		for( int j = 0; j < ritrovatore.lunghezzaParametri(); j++ ) {
			System.out.println(String.format("Parametro %s %d: ", metodo, j));
			String a = s.nextLine();
			parametri.add( a );
		}
		
		s.close();
		
		Object ritorno = ritrovatore.invocare(construtorriParametri, parametri);
		System.out.println( "Ritorno: " + ritorno);
	}
	
}
