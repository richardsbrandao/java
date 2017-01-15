package com.richard.studi.dominio;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Soldi {

	private BigDecimal valore;

	public BigDecimal getValore() {
		return valore;
	}

	public void setValore(BigDecimal valore) {
		this.valore = valore;
	}
	
}
