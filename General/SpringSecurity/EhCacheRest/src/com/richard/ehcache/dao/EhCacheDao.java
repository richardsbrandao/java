package com.richard.ehcache.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.googlecode.ehcache.annotations.Cacheable;
import com.richard.ehcache.domain.Event;

public class EhCacheDao {

	private static final Log LOG = LogFactory.getLog(EhCacheDao.class); 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Cacheable(cacheName = "event_cache")
	public List<Event> findByCategory(Integer idCategory) {
		try {
			LOG.info("iniciando");
			List<Event> events = new ArrayList<Event>();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM EVENT WHERE IDCATEGORIA = ?", idCategory);
			LOG.info("Fez a consulta retornando: " + rows);
			for( Map<String, Object> row : rows ) {
				Event event = new Event();
				event.setId((Long) row.get("IDEvento"));
				event.setIdCategoria((Long) row.get("IDCategoria") );
				event.setName((String) row.get("StNome") );
				events.add(event);
			}
			LOG.info("Retornando geral");
			return events;
		} catch(EmptyResultDataAccessException e) {
			return new ArrayList<Event>();
		}
	}
	
}
