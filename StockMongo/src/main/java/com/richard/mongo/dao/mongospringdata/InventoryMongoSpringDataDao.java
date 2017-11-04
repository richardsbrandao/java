package com.richard.mongo.dao.mongospringdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.richard.mongo.dao.InventoryDao;
import com.richard.mongo.domain.Inventory;
import com.richard.mongo.domain.Purchase;

public class InventoryMongoSpringDataDao implements InventoryDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Inventory> findAll() {
		return mongoTemplate.findAll(Inventory.class);
	}

	@Override
	public void save(Inventory inventory) {
		mongoTemplate.insert(inventory);
	}
	
	@Override
	public void removeSku(int sku) {
		Query query = new Query( Criteria.where("sku").is(sku) );
		mongoTemplate.remove(query, Inventory.class);
	}
	
	@Override
	public void purchase(Purchase purchase) {
//		mongoTemplate.updateFirst(new Query(), update, entityClass)
	}


	@Override
	public List<Inventory> findAvailableStocks() {
		return null;
	}

	@Override
	public List<Inventory> findAvailableStocksInSubInventory() {
		return null;
	}

	@Override
	public List<Inventory> findAvailableStocksWithType() {
		return null;
	}

	@Override
	public List<Inventory> findStocksWithAvailableQuantityGreatherThan() {
		return null;
	}

	@Override
	public Inventory findBySku(String sku) {
		return null;
	}

	@Override
	public Inventory findBySkuAvailableStocks() {
		return null;
	}

	@Override
	public Inventory findSpecificStock(Integer sku, Integer subInventory, Integer type) {
//		db.inventory.find({sku:8080}, { stocks: {$elemMatch: {subInventory:7701, type:1}} });
		Criteria skuCriteria = Criteria.where("sku").is(sku);
		Criteria stocksCriteria = Criteria.where("stocks").elemMatch(Criteria.where("subInventory").is(subInventory).and("type").is(type));
		
		BasicQuery query = new BasicQuery(skuCriteria.getCriteriaObject(), stocksCriteria.getCriteriaObject());
		
		return mongoTemplate.findOne(query, Inventory.class);
	}

}
