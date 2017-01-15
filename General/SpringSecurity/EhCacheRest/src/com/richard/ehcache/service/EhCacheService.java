package com.richard.ehcache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.richard.ehcache.dao.EhCacheDao;
import com.richard.ehcache.domain.Event;

public class EhCacheService {

	@Autowired
	private EhCacheDao ehcacheDao;

	public List<Event> findByCategory(int idCategory) {
		return ehcacheDao.findByCategory(idCategory);
	}
	
}
