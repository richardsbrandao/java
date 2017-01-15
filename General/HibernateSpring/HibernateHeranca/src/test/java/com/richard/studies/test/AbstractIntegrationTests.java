package com.richard.studies.test;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Ignore
@ContextConfiguration({"classpath:spring/applicationContext.xml", "classpath:spring/dataSource.xml", "classpath:spring/integrationContext.xml"})
@Transactional(propagation=Propagation.REQUIRED)
public class AbstractIntegrationTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	private DataBaseTestConfigurer dataBaseTestConfigurer;
	@Autowired
	private SqlResourceBundle sqlResourceBundle;
	
	@Before
	public void before() throws UnitTestException {
		dataBaseTestConfigurer.prepareIntegrationDatabase();
	}

	
}
