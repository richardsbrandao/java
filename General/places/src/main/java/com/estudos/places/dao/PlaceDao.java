package com.estudos.places.dao;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.estudos.places.domain.Place;

public interface PlaceDao extends MongoRepository<Place, String> {
	
	List<Place> findByPositionNear(Point point, Distance distance);
	
}
