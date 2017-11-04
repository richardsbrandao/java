package com.richard.mongo.dao;

import java.util.List;

import com.richard.mongo.domain.Inventory;
import com.richard.mongo.domain.Purchase;

public interface InventoryDao {
	
	List<Inventory> findAll();
	List<Inventory> findAvailableStocks();
	List<Inventory> findAvailableStocksInSubInventory();
	List<Inventory> findAvailableStocksWithType();
	List<Inventory> findStocksWithAvailableQuantityGreatherThan();
	
	Inventory findBySku(String sku);
	Inventory findBySkuAvailableStocks();
	Inventory findSpecificStock(Integer sku, Integer subInventory, Integer type);
	
	void save(Inventory inventory);
	void removeSku(int sku);
	void purchase(Purchase purchase);

}
