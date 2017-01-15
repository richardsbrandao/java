package riflessione.test.comparatore;

import riflessione.studi.comparatore.ReflessioneComparatore;
import riflessione.studi.comparatore.dominio.Autore;
import riflessione.studi.comparatore.dominio.Libro;

public class ComparatoreTeste {

	public static void main(String[] args) {
		Libro libroUne = new Libro("Refactoring", 	50, 80.90, 2015, new Autore("Robert Martin", 	new String[]{"Inglese", "Italiano"}));
		Libro libroDue = new Libro("JUnit", 		20, 60.40, 2014, new Autore("Kent Beck", 		new String[]{"Portoghese"}));
		Libro libroTre = new Libro("Refactoring", 	48, 80.80, 2011, new Autore("Robert Martin", 	new String[]{"Inglese"}));
		Libro libroQuattro = new Libro("Refactoring", 	48, 80.80, 2011, new Autore("Robert Martin", 	new String[]{"Inglese"}));
		
		System.out.println( new ReflessioneComparatore().confrontare(libroUne, new Autore()) );
		System.out.println("-----------");
		System.out.println( new ReflessioneComparatore().confrontare(libroUne, libroDue) );
		System.out.println("-----------");
		System.out.println( new ReflessioneComparatore().confrontare(libroUne, libroTre) );
		System.out.println("-----------");
		System.out.println( new ReflessioneComparatore().confrontare(libroQuattro, libroTre) );
	}
	
}
