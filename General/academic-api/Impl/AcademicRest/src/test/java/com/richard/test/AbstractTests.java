package com.richard.test;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public abstract class AbstractTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private DriverManagerDataSource dataSource;
	
	@Before
	public void setUp() {
		setDataSource( dataSource );
		executeSqlScript(getPrepareScriptSqlFileName(), false);
	}
	
	public abstract String getPrepareScriptSqlFileName();
	
}
