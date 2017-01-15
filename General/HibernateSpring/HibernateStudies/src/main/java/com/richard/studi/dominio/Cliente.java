package com.richard.studi.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="Cliente")
public class Cliente {

	@Id
	@SequenceGenerator(name = "cliente_id", sequenceName = "cliente_id")
	@GeneratedValue(generator = "cliente_id", strategy = GenerationType.AUTO)
	@Column(name="IDCliente")
	private Long id;
	@Column(name="StNome")
	private String nome;
	@Column(name="StEmail")
	private String email;
	@Column(name="StSenha")
	private String password;
	@Column(name="DtNascimento")
	private Date nascita;
	@OneToOne(mappedBy="cliente")
	@Cascade(CascadeType.ALL)
	private Indirizzo indirizzo;
	@OneToMany(mappedBy="cliente")
	private List<Ordine> ordine;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getNascita() {
		return nascita;
	}
	public void setNascita(Date nascita) {
		this.nascita = nascita;
	}
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}
	public List<Ordine> getOrdine() {
		return ordine;
	}
	public void setOrdine(List<Ordine> ordine) {
		this.ordine = ordine;
	}
	
}
