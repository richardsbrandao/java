package com.richard.studi.dominio;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class OrdineDettagliId implements Serializable {

	@ManyToOne
	@JoinColumn(name="IDProduto")
	private Prodotto prodotto;
	@ManyToOne
	@JoinColumn(name="IDPedido")
	private Ordine ordine;
	
	public Prodotto getProdotto() {
		return prodotto;
	}
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	public Ordine getOrdine() {
		return ordine;
	}
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

}
