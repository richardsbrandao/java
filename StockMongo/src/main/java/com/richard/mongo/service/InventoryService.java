package com.richard.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.richard.mongo.dao.InventoryDao;
import com.richard.mongo.domain.Inventory;

public class InventoryService {

//	private InventoryDao subInventoryMongoClientDao;
	@Autowired
	private InventoryDao inventoryMongoSpringDataDao;
	
	public void create() {
		
	}

	public List<Inventory> findAll() {
		return inventoryMongoSpringDataDao.findAll();
	}
	
}
