package com.richard.studi.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class Utente {

	@Id
	@SequenceGenerator(name = "utente_id", sequenceName = "utente_id")
	@GeneratedValue(generator = "utente_id", strategy = GenerationType.AUTO)
	@Column(name="IDUsuario")
	private Long id;
	@Column(name="StNome")
	private String nome;
	@Column(name="DtNascimento")
	@Type(
			type="org.jadira.usertype.dateandtime.joda.PersistentDateTime", 
			parameters = { 
					@Parameter(name = "databaseZone", value = "jvm"),
					@Parameter(name = "javaZone", value = "jvm")
			}
		 )
	private DateTime nascita;

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
	public DateTime getNascita() {
		return nascita;
	}
	public void setNascita(DateTime nascita) {
		this.nascita = nascita;
	}
	
}
