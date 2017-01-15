package com.richard.studi.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.richard.studi.dao.impl.UtenteDao;
import com.richard.studi.dominio.Utente;
import com.richard.studi.utilita.CalendarioUtilita;

@RunWith(SpringJUnit4ClassRunner.class)
public class UtenteDaoTests extends IntegrazioneAbstrattoTests {

	@Autowired
	private UtenteDao utenteDao;
	
	@Autowired
	private CalendarioUtilita calendarioUtilita;
	
	@Test
	public void trovareDaId() {
		Utente utente = utenteDao.findById(1L);
		
		assertEquals( 1988, utente.getNascita().getYear() );
		assertEquals( 9, utente.getNascita().getMonthOfYear() );
		assertEquals( 4, utente.getNascita().getDayOfMonth() );
	}
	
}
