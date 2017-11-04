package com.richard.mongo;

//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//@ContextConfiguration({"classpath:spring/applicationContext.xml", "classpath:spring/dataContext.xml"})
@Transactional(propagation=Propagation.REQUIRED)
public class AbstractIntegrationTest /*extends AbstractTransactionalJUnit4SpringContextTests*/ {

	
	
}
