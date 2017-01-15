package com.richard.studi.dao.impl;

import com.richard.studi.dominio.Utente;

public class UtenteDao extends CriteriDaoAbstratto<Utente> {

	public Utente findById(Long id) {
		return super.findById(id, Utente.class);
	}
	
}
