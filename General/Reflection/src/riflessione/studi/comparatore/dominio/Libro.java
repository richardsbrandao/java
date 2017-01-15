package riflessione.studi.comparatore.dominio;

import riflessione.studi.comparatore.biblioteca.NonConfrontare;
import riflessione.studi.comparatore.biblioteca.Profondo;
import riflessione.studi.comparatore.biblioteca.Tolleranza;

public class Libro {

	private String nome;
	private Integer stock;
	@Tolleranza(0.5)
	private Double prezzo;
	@NonConfrontare
	private Integer anno;
	@Profondo
	private Autore autore;
	
	public Libro(String nome, Integer stock, Double prezzo, Integer anno, Autore autore) {
		this.nome = nome;
		this.stock = stock;
		this.prezzo = prezzo;
		this.anno = anno;
		this.autore = autore;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	public Autore getAutore() {
		return autore;
	}
	public void setAutore(Autore autore) {
		this.autore = autore;
	}

}
