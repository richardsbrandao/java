package com.richard.studi.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.richard.studi.dao.impl.IndirizzoDao;
import com.richard.studi.dominio.Cliente;
import com.richard.studi.dominio.Indirizzo;

@RunWith(SpringJUnit4ClassRunner.class)
public class IndirizzoDaoTests extends IntegrazioneAbstrattoTests {

	@Autowired
	private IndirizzoDao indirizzoDao;
	
	@Test
	public void trovareTutto() {
		assertEquals( 3, indirizzoDao.trovareTutto().size() );
	}
	
	@Test
	public void trovareDaCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		Indirizzo indirizzo = indirizzoDao.trovareDaCliente(cliente);
		
		assertNotNull(indirizzo);
		assertEquals( new Long(1), indirizzo.getId() );
		assertEquals( "Rua Ferraz", indirizzo.getNome() );
	}
	
	@Test
	public void trovareDaId() {
		Indirizzo indirizzo = indirizzoDao.trovareDaId(1L);
		
		assertNotNull( indirizzo );
		assertEquals( new Long(1), indirizzo.getId() );
		assertEquals( new Long(1), indirizzo.getCliente().getId() );
	}
	
	@Test
	public void contare() {
		assertEquals( new Long(3), indirizzoDao.contare() );
	}
	
}
