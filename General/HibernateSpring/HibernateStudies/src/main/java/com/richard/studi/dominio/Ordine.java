package com.richard.studi.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="Ordine")
public class Ordine {

	@Id
	@SequenceGenerator(name = "order_id", sequenceName = "order_id")
	@GeneratedValue(generator = "order_id", strategy = GenerationType.AUTO)
	@Column(name="IDPedido")
	private Long id;
	@Column(name="DtData")
	private Date giorno;
	@Column(name="FoFrete")
	private Double trasporto;
	@ManyToOne(targetEntity=Cliente.class)
	@JoinColumn(name="IDCliente", referencedColumnName="IDCliente", nullable=false)
	private Cliente cliente;
	@OneToMany(mappedBy="id.ordine")
	@Cascade(value=CascadeType.ALL)
	private List<OrdineDettagli> ordineDettagli;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getGiorno() {
		return giorno;
	}
	public void setGiorno(Date giorno) {
		this.giorno = giorno;
	}
	public Double getTrasporto() {
		return trasporto;
	}
	public void setTrasporto(Double trasporto) {
		this.trasporto = trasporto;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<OrdineDettagli> getOrdineDettagli() {
		return ordineDettagli;
	}
	public void setOrdineDettagli(List<OrdineDettagli> ordineDettagli) {
		this.ordineDettagli = ordineDettagli;
	}
	
}
