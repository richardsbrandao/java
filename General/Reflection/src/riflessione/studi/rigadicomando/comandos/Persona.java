package riflessione.studi.rigadicomando.comandos;

import java.util.Date;
import java.util.List;

import riflessione.studi.rigadicomando.configs.Comando;
import riflessione.studi.rigadicomando.configs.Parametro;

@Comando
public class Persona {

	@Parametro(value="-n", opzionale=false)
	private String nome;
	@Parametro(value="-t")
	private Boolean lavora;
	@Parametro(value="-s")
	private Double stipendio;
	@Parametro(value="-b", opzionale=false)
	private Date compleanno;
	@Parametro(value="-f")
	private List<String> famiglia;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLavora(Boolean lavora) {
		this.lavora = lavora;
	}

	public void setStipendio(Double stipendio) {
		this.stipendio = stipendio;
	}

	public void setCompleanno(Date compleanno) {
		this.compleanno = compleanno;
	}

	public void setFamiglia(List<String> famiglia) {
		this.famiglia = famiglia;
	}

	@Override
	public String toString() {
		return "Persona [nome=" + nome + ", lavora=" + lavora + ", stipendio="
				+ stipendio + ", compleanno=" + compleanno + ", famiglia="
				+ famiglia + "]";
	}
	
//	persona -t true -s 5000 -n Richard -b 26/11/1991 -f Ketherin, Cristina, Carlos
	
}
