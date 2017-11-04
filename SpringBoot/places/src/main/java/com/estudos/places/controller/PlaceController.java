package com.estudos.places.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.places.domain.Place;
import com.estudos.places.service.PlaceService;
import com.richard.errors.BadRequestException;

@RestController
@RequestMapping(value="/places")
@Profile("dev")
public class PlaceController {

	@Autowired
	private PlaceService placeService;
	
	@Value("${teste.de.profile}")
	private String profile;
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String profile() {
		return profile;
	}
	
	@RequestMapping(value="/place", method=RequestMethod.POST)
	public Place create(@RequestBody Place place) {
		return placeService.save(place);
	}
	
	@RequestMapping(value="/place")
	public List<Place> findAll() {
		return placeService.findAll();
	}
	
	@RequestMapping(value="/place/filter", method=RequestMethod.GET)
	public List<Place> findGeo(@RequestParam(value="lat") String lat, @RequestParam(value="lon") String lon) throws BadRequestException {
//		http://localhost:8080/places/place/filter?lat=52&lon=52
		return placeService.findByPosition(lat, lon);
	}
	
}
