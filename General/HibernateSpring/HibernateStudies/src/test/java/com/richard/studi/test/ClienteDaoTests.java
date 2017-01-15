package com.richard.studi.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.richard.studi.dao.impl.ClienteDao;
import com.richard.studi.dominio.Cliente;
import com.richard.studi.utilita.CalendarioUtilita;
import com.richard.studi.utilita.Mese;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClienteDaoTests extends IntegrazioneAbstrattoTests {

	@Autowired
	private ClienteDao clienteDao;
	@Autowired
	private CalendarioUtilita calendarioUtilita;
	
	@Test
	public void salvareCliente() {
		Cliente cliente = new Cliente();
		cliente.setEmail("richardsbrandao@gmail.com");
		cliente.setNome("Richard");
		cliente.setPassword("123456");
		cliente.setNascita( calendarioUtilita.adDate(26, Mese.NOVEMBRE.meseId, 1991) );

		clienteDao.save( cliente );
		
		Cliente clienteSalvato = ottenereClientePerAssert( cliente.getId() );
		
		assertNotNull( clienteSalvato.getId() );
		assertEquals( "richardsbrandao@gmail.com", clienteSalvato.getEmail() );
		assertEquals( "Richard", clienteSalvato.getNome() );
		assertEquals( "123456", clienteSalvato.getPassword() );
	}
	
	@Test
	public void trovareDaId() {
		Cliente customer = clienteDao.findById(1L);
		
		assertEquals( new Long(1), customer.getId() );
		assertEquals( "ketherin_hp@hotmail.com", customer.getEmail() );
		assertEquals( "Ketherin Felix", customer.getNome() );
		assertEquals( "123456", customer.getPassword() );
	}
	
	@Test
	public void cancellareCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		clienteDao.delete(cliente);
		
		try {
			ottenereClientePerAssert(1L);
			fail("Não deletou o Customer");
		} catch(AssertionError e) {  }
	}
	
	@Test
	public void aggiornareCliente() {
//		customerDao.save(customer);
	}
	
	@Test
	public void trovareTutto() {
		List<Cliente> cliente = clienteDao.trovareTutto();
		assertEquals( 4, cliente.size() );
	}
	
	@Test
	public void trovareTra() {
		Date dataDiInizio = calendarioUtilita.adDate(1, Mese.GENNAIO.calendarId, 1991);
		Date dataDiFinale = calendarioUtilita.adDate(1, Mese.DICEMBRE.calendarId, 1991);
		
		List<Cliente> clienti = clienteDao.trovareTra(dataDiInizio, dataDiFinale);
		
		assertEquals( 1, clienti.size() );
		assertEquals( new Long(1), clienti.get(0).getId() );
	}
	
	@Test
	public void trovareDa() {
		List<Cliente> clienti = clienteDao.trovareDaEmailEPassword("caf_brandao@ig.com.br", "654321");
		
		assertEquals( 1, clienti.size() );
		assertEquals( new Long(2), clienti.get(0).getId() );
	}
	
	@Test
	public void findAllByExample() {
		Cliente customer = new Cliente();
		customer.setEmail("caf_brandao@ig.com.br");
		
		List<Cliente> customers = clienteDao.findByCustomer( customer );
		
		assertEquals( 2, customers.size() );
	}
	
	@Test
	public void findOneByExample() {
		Cliente example = new Cliente();
		example.setEmail("caf_brandao@ig.com.br");
		example.setPassword("654321");
		
		Cliente customer = clienteDao.trovareUnoDaCliente(example);
		
		assertEquals( new Long(2), customer.getId() );
	}
	
	@Test
	public void contare() {
		assertEquals( new Integer(2), clienteDao.contareDaEmail("caf_brandao@ig.com.br") );
		assertEquals( new Integer(4), clienteDao.contare() );
	}
	
}
