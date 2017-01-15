package com.estudos.ejbtest.ejb_test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TelephoneDaoIntegrationTest {

	private InitialContext initialContext;
	
	@Before
	public void setUp() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
        properties.setProperty("tutorial", "new://Resource?type=DataSource");
        properties.setProperty("tutorial.JdbcDriver", "com.mysql.jdbc.Driver");
        properties.setProperty("tutorial.JdbcUrl", "jdbc:mysql://localhost/python");
        properties.setProperty("tutorial.UserName", "root");
        properties.setProperty("tutorial.Password", "");
        
        initialContext = new InitialContext(properties);
	}
	
	@Test
    public void TelephoneService() throws Exception {
        Object object = initialContext.lookup("TelephoneServiceLocal");
        
        assertNotNull(object);
		assertTrue(object instanceof ITelephoneService);
		ITelephoneService service = (ITelephoneService) object;
		
		service.save();
    }
	
}
