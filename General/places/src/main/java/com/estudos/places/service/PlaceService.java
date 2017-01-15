package com.estudos.places.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.estudos.places.dao.PlaceDao;
import com.estudos.places.domain.Place;
import com.estudos.places.validator.PlaceValidator;
import com.richard.errors.BadRequestException;

@Service
public class PlaceService {

	@Autowired
	private PlaceValidator placeValidator;
	
	@Autowired
	private PlaceDao placeDao;
	
	public Place save(Place place) {
		return placeDao.save(place);
	}
	
	public List<Place> findAll() {
		return placeDao.findAll();
	}
	
	public List<Place> findByPosition(String lat, String lon) throws BadRequestException {
		placeValidator.validate(lat, lon);
		Point point = new Point(Double.valueOf(lat), Double.valueOf(lon));
		Distance distance = new Distance(20000, Metrics.KILOMETERS);
		return placeDao.findByPositionNear(point, distance);
	}
	
}
