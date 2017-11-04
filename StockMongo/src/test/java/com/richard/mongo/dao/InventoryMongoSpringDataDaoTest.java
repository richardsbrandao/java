package com.richard.mongo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring/applicationContext.xml", "classpath:spring/dataContext.xml"})
public class InventoryMongoSpringDataDaoTest {
	
	@Autowired
	private MongoTemplate mongoTemplate;

//	@Test
	public void testVa() {
		System.out.println("a");
	}
	
}
