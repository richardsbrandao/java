package riflessione.studi.situazionescolastica;

import javax.swing.JOptionPane;

public class SituazioneScolastica {

	private Integer provaUno;
	private Integer provaDue;
	private Integer provaTre;
	
	public SituazioneScolastica(String provaUno, String provaDue, String provaTre) {
		this.provaUno = Integer.valueOf(provaUno);
		this.provaDue = Integer.valueOf(provaDue);
		this.provaTre = Integer.valueOf(provaTre);
	}
	
	public Integer getProvaUno() {
		return provaUno;
	}
	
	public Integer getProvaDue() {
		return provaDue;
	}
	
	public Integer getProvaTre() {
		return provaTre;
	}
	
	public String valutare(String prova) {
		try {
			String nomeDellMetodo = "get" + prova.substring(0,1).toUpperCase() + prova.substring(1);
			Integer punto = (Integer) this.getClass().getDeclaredMethod(nomeDellMetodo).invoke(this);
			if( punto > 6 ) {
				return "Approvato";
			} else if( punto > 4 ) {
				return "Recupero";
			} else {
				return "Fallire";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		String provaUno = JOptionPane.showInputDialog("Punti prima prova: ");
		String provaDue = JOptionPane.showInputDialog("Punti seconda prova: ");
		String provaTre = JOptionPane.showInputDialog("Punti terza prova: ");
		
		SituazioneScolastica situazione = new SituazioneScolastica(provaUno, provaDue, provaTre);
		
		String valutazione = situazione.valutare(JOptionPane.showInputDialog("Punto a valutare: "));
		JOptionPane.showMessageDialog(null, "Avalia��o: " + valutazione); 
	}
	
}
