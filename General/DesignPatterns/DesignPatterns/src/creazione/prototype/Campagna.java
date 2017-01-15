package creazione.prototype;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Campagna implements Prototipo<Campagna> {

	private String nome;
	private Calendar maturita;
	private Set<String> paroleChiave;
	
	public Campagna(String nome, Calendar maturita, Set<String> paroleChiave) {
		this.nome = nome;
		this.maturita = maturita;
		this.paroleChiave = paroleChiave;
	}
	
	public Campagna clone() {
		String nome = "Cópia da campanha: " + this.nome;
		Calendar maturita = (Calendar) this.maturita.clone();
		Set<String> paroleChiave = new HashSet<String>( this.paroleChiave );
		
		return new Campagna(nome, maturita, paroleChiave);
	}

}
