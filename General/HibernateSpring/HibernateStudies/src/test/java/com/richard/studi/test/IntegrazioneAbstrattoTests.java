package com.richard.studi.test;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.richard.studi.dominio.Cliente;

@Ignore
@ContextConfiguration({"classpath:spring/applicationContext.xml", "classpath:spring/dataSource.xml", "classpath:spring/integrationContext.xml"})
@Transactional(propagation=Propagation.REQUIRED)
public class IntegrazioneAbstrattoTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataBaseTestConfiguratore dataBaseTestConfigurer;
	
	@Before
	public void before() throws UnitTestException {
		dataBaseTestConfigurer.prepareIntegrationDatabase();
	}

	public Cliente ottenereClientePerAssert(Long id) {
		Cliente cliente = (Cliente) sessionFactory.getCurrentSession().createCriteria(Cliente.class).add( Restrictions.eqOrIsNull("id", id) ).uniqueResult();
		if( cliente == null ) {
			throw new AssertionError();
		}
		return cliente;
	}
	
}
