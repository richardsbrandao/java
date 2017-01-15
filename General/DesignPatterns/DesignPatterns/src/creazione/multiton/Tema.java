package creazione.multiton;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Tema {

	private static final String SKY = "Sky";
	private static final String FIRE = "Fire";
	
	private static Map<String, Tema> instanze = new HashMap<String, Tema>();
	
	private String nome;
	private Color coloreDiSfondo;
	private Color coloreDiCarattere;

	static {
		Tema temaUno = new Tema();
		temaUno.setNome( SKY );
		temaUno.setColoreDiSfondo( Color.BLUE );
		temaUno.setColoreDiCarattere( Color.BLACK );
		
		Tema temaDue = new Tema();
		temaDue.setNome( FIRE );
		temaDue.setColoreDiSfondo( Color.RED );
		temaDue.setColoreDiCarattere( Color.WHITE );
		
		instanze.put(temaUno.getNome(), temaUno);
		instanze.put(temaDue.getNome(), temaDue);
	}
	
	public static Tema ottenereInstanza(String nomeDelTema) {
		return Tema.instanze.get( nomeDelTema );
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Color getColoreDiSfondo() {
		return coloreDiSfondo;
	}
	public void setColoreDiSfondo(Color coloreDiSfondo) {
		this.coloreDiSfondo = coloreDiSfondo;
	}
	public Color getColoreDiCarattere() {
		return coloreDiCarattere;
	}
	public void setColoreDiCarattere(Color coloreDiCarattere) {
		this.coloreDiCarattere = coloreDiCarattere;
	}
	
}
