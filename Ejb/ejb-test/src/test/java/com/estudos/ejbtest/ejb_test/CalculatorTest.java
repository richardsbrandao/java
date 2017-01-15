package com.estudos.ejbtest.ejb_test;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculatorTest {

	private InitialContext initialContext;
	
	@Before
	public void setUp() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
//        http://tomee.apache.org/examples-trunk/injection-of-entitymanager/README.html
        initialContext = new InitialContext(properties);
	}
	
	@Test
    public void testCalculatorViaRemoteInterface() throws Exception {
        Object object = initialContext.lookup("CalculatorImplLocal");

		assertNotNull(object);
		assertTrue(object instanceof CalculatorLocal);
		CalculatorLocal calc = (CalculatorLocal) object;
		assertEquals(10, calc.sum(4,6));
		assertEquals(12, calc.multiply(3,4));
    }

	
}
