package creazione.builder;

import java.util.Calendar;

public class Billetta {
	
	private String trattario;
	private String cedente;
	private Double valore;
	private Calendar maturita;
	private String nostroNumero;
	private String Logo;
	private String barcode;
	
	public String getTrattario() {
		return trattario;
	}
	public void setTrattario(String trattario) {
		this.trattario = trattario;
	}
	public String getCedente() {
		return cedente;
	}
	public void setCedente(String cedente) {
		this.cedente = cedente;
	}
	public Double getValore() {
		return valore;
	}
	public void setValore(Double valore) {
		this.valore = valore;
	}
	public Calendar getMaturita() {
		return maturita;
	}
	public void setMaturita(Calendar maturita) {
		this.maturita = maturita;
	}
	public String getNostroNumero() {
		return nostroNumero;
	}
	public void setNostroNumero(String nostroNumero) {
		this.nostroNumero = nostroNumero;
	}
	public String getLogo() {
		return Logo;
	}
	public void setLogo(String logo) {
		Logo = logo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	@Override
	public String toString() {
		String formattataMaturita = maturita.get(Calendar.DAY_OF_MONTH) + "/" + maturita.get(Calendar.MONTH) + "/" + maturita.get(Calendar.YEAR); 
		return "Billetta [trattario=" + trattario + ", cedente=" + cedente
				+ ", valore=" + valore + ", maturita=" + formattataMaturita
				+ ", nostroNumero=" + nostroNumero + ", Logo=" + Logo
				+ ", barcode=" + barcode + "]";
	}
	
}
