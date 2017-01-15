package riflessione.studi.rmapper.domain;

import java.util.Date;

import riflessione.studi.rmapper.uzansa.RIgnorare;
import riflessione.studi.rmapper.uzansa.RProprieta;

public class Serie {
	
	private String nome;
	private int stagione;
	private int capitolo;
	private Date inizio;

	public Serie(String nome, int stagione, int capitolo, Date inizio) {
		this.nome = nome;
		this.stagione = stagione;
		this.capitolo = capitolo;
		this.inizio = inizio;
	}

	@RProprieta(nome="serie")
	public String getNome() {
		return nome;
	}

	public int getStagione() {
		return stagione;
	}

	public int getCapitolo() {
		return capitolo;
	}

	@RIgnorare
	public Date getInizio() {
		return inizio;
	}
	
}
