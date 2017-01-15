package studi.hibernate;

import studi.hibernate.dao.StudenteDao;
import studi.hibernate.dominio.Studente;

public class ModoOO {
	
	public static void main(String[] args) {
		Studente studente = new Studente();
		studente.setId(1L);
		studente.setNome("def");
		
		StudenteDao studenteDao = new StudenteDao();
		studenteDao.savar(studente);
	}
	
}
