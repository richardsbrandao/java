package com.richard.studi.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Indirizzo")
public class Indirizzo {

	@Id
	@SequenceGenerator(name = "indirizzo_id", sequenceName = "indirizzo_id")
	@GeneratedValue(generator = "indirizzo_id", strategy = GenerationType.AUTO)
	@Column(name="IDEndereco")
	private Long id;
	@Column(name="StNome", nullable=false)
	private String nome;
	@Column(name="StNumero", nullable=false)
	private String numero;
	@Column(name="StComplemento")
	private String complemento;
	@OneToOne
	@JoinColumn(name="IDCliente", referencedColumnName="IDCliente", nullable=false)
	private Cliente cliente;
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
