package com.richard.ehcache.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.richard.ehcache.domain.Event;

public class EventMapper implements RowMapper<Event> {

	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = new Event();
		event.setId(rs.getLong("IDEvento"));
		event.setIdCategoria( rs.getLong("IDCategoria") );
		event.setName( rs.getString("StNome") );
		return event;
	}

}
