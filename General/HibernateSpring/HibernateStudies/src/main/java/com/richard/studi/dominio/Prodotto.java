package com.richard.studi.dominio;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="Prodotto")
public class Prodotto {

	@Id
	@SequenceGenerator(name = "product_id", sequenceName = "product_id")
	@GeneratedValue(generator = "product_id", strategy = GenerationType.AUTO)
	@Column(name="IDProduto")
	private Long id;
	@Column(name="StNome")
	private String nome;
	@Column(name="StCategoria")
	private String categoria;
	@Column(name="BoInStock")
	private Boolean inMagazzino;
	@Embedded
	@AttributeOverride(name="valore", column=@Column(name="FoPreco"))
	private Soldi prezzo;
	@OneToMany(mappedBy="id.prodotto")
	@Cascade(value=CascadeType.ALL)
	private List<OrdineDettagli> ordineDettagli;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Boolean getInMagazzino() {
		return inMagazzino;
	}
	public void setInMagazzino(Boolean inMagazzino) {
		this.inMagazzino = inMagazzino;
	}
	public Soldi getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Soldi prezzo) {
		this.prezzo = prezzo;
	}
	public List<OrdineDettagli> getOrdineDettagli() {
		return ordineDettagli;
	}
	public void setOrdineDettagli(List<OrdineDettagli> ordineDettagli) {
		this.ordineDettagli = ordineDettagli;
	}

}
