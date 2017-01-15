package studi.hibernate.dao;

import studi.hibernate.dominio.Studente;

public class StudenteDao extends DaoAbstratto<Studente> {

	public void savar(Studente studente) {
		super.salva(studente);
	}
	
}
