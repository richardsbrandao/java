package riflessione.studi.attributo;

public abstract class Account {

	private String nome;
	public String agenzia;
	public String account;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAgenzia() {
		return agenzia;
	}
	public void setAgenzia(String agenzia) {
		this.agenzia = agenzia;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
}
