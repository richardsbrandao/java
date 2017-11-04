package com.richard.mongo.main;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.richard.mongo.dao.mongospringdata.InventoryMongoSpringDataDao;

public class SpringDataMain {

	static final Logger logger = Logger.getLogger(SpringDataMain.class);
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		ApplicationContext ctx = new GenericXmlApplicationContext("spring/applicationContext.xml");
		InventoryMongoSpringDataDao springDataDao = (InventoryMongoSpringDataDao) ctx.getBean("inventoryMongoSpringDataDao");
		
//		List<Inventory> skus = springDataDao.findAll();
//		for(Inventory sku : skus) {
//			System.out.println("--------------");
//			System.out.println( sku );
//		}
//		
//		Inventory newInventory = new Inventory(9000, Arrays.asList(new Stock(4601, 46, 1, 10, 0)));
//		springDataDao.save(newInventory);
//		
//		springDataDao.removeSku(9000);
//		
//		Inventory findSpecificStock = springDataDao.findSpecificStock(8080, 7701, 3);
//		System.out.println("--------------");
//		System.out.println("SpecificStock: " + findSpecificStock);
//		
//		Purchase purchase = new Purchase(8080, 7701, 1, 1);
//		springDataDao.purchase(purchase);
		
		
	}

}
