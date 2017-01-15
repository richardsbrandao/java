package riflessione.studi.comparatore.dominio;


public class Autore {

	private String nome;
	private String[] lingua;
	
	public Autore(String nome, String[] lingua) {
		this.nome = nome;
		this.lingua = lingua;
	}

	public Autore() {  }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String[] getLingua() {
		return lingua;
	}

	public void setLingua(String[] lingua) {
		this.lingua = lingua;
	}
	
}
