package com.richard.studi.dominio;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="OrdineDettagli")
@AssociationOverrides({
	@AssociationOverride(name="pk.product", joinColumns=@JoinColumn(name="IDProduto")),
	@AssociationOverride(name="pk.order", joinColumns=@JoinColumn(name="IDPedido"))
})
public class OrdineDettagli {
	
	@EmbeddedId
	private OrdineDettagliId id;
	@Column(name="FoPreco")
	private Double prezzo;
	@Column(name="ItQuantidade")
	private Integer quantita;
	@Column(name="FoDesconto")
	private Double sconto;
	
	@Transient
	public Ordine getOrdine() {
		return getId().getOrdine();
	}
 
	public void setOrdine(Ordine ordine) {
		getId().setOrdine(ordine);
	}
 
	@Transient
	public Prodotto getProdotto() {
		return getId().getProdotto();
	}
 
	public void setProdotto(Prodotto prodotto) {
		getId().setProdotto(prodotto);
	}

	public OrdineDettagliId getId() {
		return id;
	}

	public void setId(OrdineDettagliId id) {
		this.id = id;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Double getSconto() {
		return sconto;
	}

	public void setSconto(Double sconto) {
		this.sconto = sconto;
	}
	
}
